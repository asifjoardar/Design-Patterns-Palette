import org.asif.app.components.*;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.asif.app.configuration.StagingDeploymentConfigurationBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StagingDeploymentConfigurationBuilderTest {

    @Test
    void testBuildValidConfiguration() {
        DeploymentConfigurationBuilder builder = new StagingDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(5)
                .minInstances(2)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.CLOSED)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.STANDARD);

        DeploymentConfiguration config = builder.build();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(5, config.getMaxInstances());
        assertEquals(2, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.CLOSED, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.STANDARD, config.getStorageType());
    }

    @Test
    void testBuildInvalidConfiguration_minInstancesGreaterThanMaxInstances() {
        DeploymentConfigurationBuilder builder = new StagingDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(2)
                .minInstances(3);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Min instances cannot exceed max instances.", thrown.getMessage());
    }

    @Test
    void testBuildInvalidConfiguration_missingRequiredFields() {
        DeploymentConfigurationBuilder builder = new StagingDeploymentConfigurationBuilder();

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Environment and region must be set before building.", thrown.getMessage());
    }
}
