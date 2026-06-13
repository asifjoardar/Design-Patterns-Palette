package org.asif.app;

import org.asif.app.components.*;
import org.asif.app.configuration.ConcreteDeploymentConfigurationBuilder;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.asif.app.utils.DeploymentConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StagingDeploymentConfigurationBuilderTest {

    @Test
    void testBuildValidConfiguration() {
        DeploymentConfigurationBuilder builder = new ConcreteDeploymentConfigurationBuilder();

        DeploymentConfiguration config = builder
                .environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_STAGING)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_STAGING)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.CLOSED)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.STANDARD)
                .build();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES_STAGING, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES_STAGING, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.CLOSED, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.STANDARD, config.getStorageType());
    }

    @Test
    void testBuildInvalidConfigurationWithMinInstancesGreaterThanMaxInstances() {
        DeploymentConfigurationBuilder builder = new ConcreteDeploymentConfigurationBuilder();

        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_STAGING)
                .minInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_STAGING);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Min instances cannot exceed max instances.", thrown.getMessage());
    }

    @Test
    void testBuildInvalidConfigurationWithMissingRequiredFields() {
        DeploymentConfigurationBuilder builder = new ConcreteDeploymentConfigurationBuilder();
        assertThrows(IllegalStateException.class, builder::build);
    }
}
