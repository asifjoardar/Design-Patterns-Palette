package org.asif.strategies;

import org.asif.util.DummyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete strategy. Implements credit card payment method.
 */
public class PayByCreditCard implements PayStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayByCreditCard.class);
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String cardNumber;
    private Map<String, Object> cardDetails = new HashMap<>();
    private boolean cardIsPresent;

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Enter the card number: ");
            cardNumber = READER.readLine();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            String expiryDate = READER.readLine();
            System.out.print("Enter the CVV code: ");
            String cvv = READER.readLine();
            System.out.print("Enter the pin number: ");
            String pin = READER.readLine();
            cardDetails = Map.of(
                    "expiryDate", expiryDate,
                    "cvv", cvv,
                    "pin", pin
            );
            if (verify()) {
                LOGGER.info("Data verification has been successful.");
            } else {
                LOGGER.warn("Invalid card details.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verify() {
        setSignedIn(cardDetails.equals(DummyData.getCreditCards().get(cardNumber)));
        return cardIsPresent;
    }

    private void setSignedIn(boolean cardIsPresent) {
        this.cardIsPresent = cardIsPresent;
    }

    /**
     * After card validation we can charge customer's credit card.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent) {
            LOGGER.info("Paying {} using Credit Card.", paymentAmount);
            return true;
        } else {
            return false;
        }
    }
}
