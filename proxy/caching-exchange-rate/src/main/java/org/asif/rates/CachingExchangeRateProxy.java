package org.asif.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The <em>proxy</em>. It implements the same {@link ExchangeRateService}
 * interface as the real service and stands in for it, but remembers every rate
 * it has already fetched. Repeated lookups for the same currency pair are served
 * straight from the cache, so the expensive remote service is hit at most once
 * per pair.
 *
 * <p>Because it shares the interface, callers cannot tell they are talking to a
 * proxy rather than the real thing — that's the whole point.</p>
 */
public final class CachingExchangeRateProxy implements ExchangeRateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CachingExchangeRateProxy.class);

    private final ExchangeRateService origin;
    private final Map<String, Double> cache = new HashMap<>();

    public CachingExchangeRateProxy(final ExchangeRateService origin) {
        this.origin = origin;
    }

    @Override
    public double getRate(final String from, final String to) {
        final String key = from + "->" + to;
        final Double cached = cache.get(key);
        if (cached != null) {
            LOGGER.info("Cache hit for {} = {} (no remote call)", key, cached);
            return cached;
        }
        final double rate = origin.getRate(from, to);
        cache.put(key, rate);
        return rate;
    }
}
