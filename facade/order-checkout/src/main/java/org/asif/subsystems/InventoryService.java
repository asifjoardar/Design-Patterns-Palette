package org.asif.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Subsystem that tracks how much of each product is on hand and reserves stock
 * for an order. One of several services the checkout facade hides behind a
 * single call.
 */
public class InventoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    private final Map<String, Integer> stock = new HashMap<>();

    /**
     * Adds units of a product to the warehouse.
     *
     * @param sku      the product identifier
     * @param quantity how many units to add
     */
    public void addStock(final String sku, final int quantity) {
        stock.merge(sku, quantity, Integer::sum);
    }

    /**
     * @param sku      the product identifier
     * @param quantity the quantity requested
     * @return whether at least {@code quantity} units are available
     */
    public boolean isInStock(final String sku, final int quantity) {
        return stock.getOrDefault(sku, 0) >= quantity;
    }

    /**
     * Removes the requested quantity from stock.
     *
     * @param sku      the product identifier
     * @param quantity how many units to reserve
     * @throws IllegalStateException if there is not enough stock
     */
    public void reserve(final String sku, final int quantity) {
        if (!isInStock(sku, quantity)) {
            throw new IllegalStateException("Not enough stock for " + sku);
        }
        stock.merge(sku, -quantity, Integer::sum);
        LOGGER.info("Reserved {} x {} ({} left)", quantity, sku, stock.get(sku));
    }

    /**
     * @param sku the product identifier
     * @return the units currently available
     */
    public int available(final String sku) {
        return stock.getOrDefault(sku, 0);
    }
}
