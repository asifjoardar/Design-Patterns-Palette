package org.asif.app;

import org.asif.app.components.*;
import org.asif.app.configuration.ConcreteDeploymentConfigurationBuilder;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationDirector;
import org.asif.app.utils.DeploymentConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies the Director orchestrates the builder into the expected predefined
 * configurations for each environment.
 */
class DeploymentConfigurationDirectorTest {

    private static DeploymentConfigurationDirector director() {
        return new DeploymentConfigurationDirector(new ConcreteDeploymentConfigurationBuilder());
    }

    @Test
    void developmentRecipeUsesBuilderDefaults() {
        DeploymentConfiguration config = director().constructDevelopmentDeploymentConfiguration();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.EU_WEST_1, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES, config.getMinInstances());
        assertFalse(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.DEFAULT, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.STANDARD, config.getStorageType());
    }

    @Test
    void stagingRecipeIsFullyConfigured() {
        DeploymentConfiguration config = director().constructStagingDeploymentConfiguration();

        assertEquals(DeploymentEnvironment.STAGING, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES_STAGING, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES_STAGING, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.RESTRICTED, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.INFO, LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.PREMIUM, config.getStorageType());
    }

    @Test
    void productionRecipeIsFullyConfigured() {
        DeploymentConfiguration config = director().constructProductionDeploymentConfiguration();

        assertEquals(DeploymentEnvironment.PRODUCTION, config.getEnvironment());
        assertEquals(Region.US_WEST_2, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.OPEN, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR),
                config.getLoggingLevels());
        assertEquals(StorageType.ARCHIVE, config.getStorageType());
    }
}
