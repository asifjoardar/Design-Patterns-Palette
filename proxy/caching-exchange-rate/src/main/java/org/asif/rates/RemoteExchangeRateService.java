package org.asif.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The <em>real subject</em>: a stand-in for a real exchange-rate API. Each call
 * is deliberately "expensive" — it sleeps to mimic a network round-trip and
 * counts how many times it was actually hit, so the caching proxy's benefit is
 * easy to see.
 */
public final class RemoteExchangeRateService implements ExchangeRateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteExchangeRateService.class);

    private static final long SIMULATED_LATENCY_MS = 120L;
    private static final double USD_TO_EUR = 0.92;
    private static final double USD_TO_GBP = 0.79;
    private static final double EUR_TO_USD = 1.09;

    private final Map<String, Double> rates = new HashMap<>();
    private int remoteCallCount;

    public RemoteExchangeRateService() {
        rates.put("USD->EUR", USD_TO_EUR);
        rates.put("USD->GBP", USD_TO_GBP);
        rates.put("EUR->USD", EUR_TO_USD);
    }

    @Override
    public double getRate(final String from, final String to) {
        remoteCallCount++;
        simulateNetworkLatency();

        final String key = from + "->" + to;
        final Double rate = rates.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("No rate available for " + key);
        }
        LOGGER.info("Expensive remote lookup #{} for {} = {}", remoteCallCount, key, rate);
        return rate;
    }

    /**
     * @return how many times the expensive remote call has actually been made
     */
    public int getRemoteCallCount() {
        return remoteCallCount;
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(SIMULATED_LATENCY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
