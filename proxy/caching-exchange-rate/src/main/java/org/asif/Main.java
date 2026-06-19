package org.asif;

import org.asif.rates.CachingExchangeRateProxy;
import org.asif.rates.ExchangeRateService;
import org.asif.rates.RemoteExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: the client only knows the {@link ExchangeRateService} interface. We hand
 * it a caching proxy instead of the real service, so repeated lookups for the
 * same pair are served from cache and the expensive remote call runs far fewer
 * times.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final RemoteExchangeRateService remote = new RemoteExchangeRateService();
        final ExchangeRateService rates = new CachingExchangeRateProxy(remote);

        // The same pair is requested three times, but only the first reaches the remote service.
        LOGGER.info("USD->EUR = {}", rates.getRate("USD", "EUR"));
        LOGGER.info("USD->EUR = {}", rates.getRate("USD", "EUR"));
        LOGGER.info("USD->GBP = {}", rates.getRate("USD", "GBP"));
        LOGGER.info("USD->EUR = {}", rates.getRate("USD", "EUR"));

        LOGGER.info("Distinct pairs requested: 2, but actual remote calls made: {}",
                remote.getRemoteCallCount());
    }
}
