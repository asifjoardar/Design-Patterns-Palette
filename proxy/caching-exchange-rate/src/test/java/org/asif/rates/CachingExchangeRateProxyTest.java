package org.asif.rates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachingExchangeRateProxyTest {

    private static final double USD_TO_EUR = 0.92;
    private static final int ONE_REMOTE_CALL = 1;
    private static final int TWO_REMOTE_CALLS = 2;

    @Test
    void proxyReturnsTheSameRateAsTheRealService() {
        RemoteExchangeRateService remote = new RemoteExchangeRateService();
        ExchangeRateService proxy = new CachingExchangeRateProxy(remote);

        assertEquals(USD_TO_EUR, proxy.getRate("USD", "EUR"));
    }

    @Test
    void repeatedLookupsForTheSamePairHitTheRemoteServiceOnce() {
        RemoteExchangeRateService remote = new RemoteExchangeRateService();
        ExchangeRateService proxy = new CachingExchangeRateProxy(remote);

        proxy.getRate("USD", "EUR");
        proxy.getRate("USD", "EUR");
        proxy.getRate("USD", "EUR");

        assertEquals(ONE_REMOTE_CALL, remote.getRemoteCallCount());
    }

    @Test
    void distinctPairsAreEachFetchedFromTheRemoteServiceOnce() {
        RemoteExchangeRateService remote = new RemoteExchangeRateService();
        ExchangeRateService proxy = new CachingExchangeRateProxy(remote);

        proxy.getRate("USD", "EUR");
        proxy.getRate("USD", "GBP");
        proxy.getRate("USD", "EUR"); // served from cache

        assertEquals(TWO_REMOTE_CALLS, remote.getRemoteCallCount());
    }
}
