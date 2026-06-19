package org.asif.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Subsystem that books a delivery and returns a tracking number.
 */
public class ShippingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingService.class);

    /**
     * Schedules a delivery for the given items.
     *
     * @param sku      the product identifier
     * @param quantity how many units to ship
     * @param address  where to deliver them
     * @return a tracking number for the shipment
     */
    public String scheduleDelivery(final String sku, final int quantity, final String address) {
        final String trackingNumber = "TRK-" + UUID.randomUUID();
        LOGGER.info("Scheduled {} x {} to {} -> {}", quantity, sku, address, trackingNumber);
        return trackingNumber;
    }
}
