package org.asif.rates;

/**
 * The <em>subject</em> interface. Both the real, expensive service and the
 * caching proxy implement this, so callers can use either one without knowing
 * which they hold.
 */
public interface ExchangeRateService {

    /**
     * Returns the conversion rate between two currencies.
     *
     * @param from the source currency code, e.g. {@code "USD"}
     * @param to   the target currency code, e.g. {@code "EUR"}
     * @return how many units of {@code to} one unit of {@code from} buys
     */
    double getRate(String from, String to);
}
