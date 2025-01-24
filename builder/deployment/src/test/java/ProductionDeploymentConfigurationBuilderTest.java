import org.asif.app.components.*;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.asif.app.configuration.ProductionDeploymentConfigurationBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductionDeploymentConfigurationBuilderTest {

    @Test
    void testBuildValidConfiguration() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(5)
                .minInstances(2)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.ERROR))
                .storageType(StorageType.PREMIUM);

        DeploymentConfiguration config = builder.build();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(5, config.getMaxInstances());
        assertEquals(2, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.OPEN, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.PREMIUM, config.getStorageType());
    }

    @Test
    void testBuildInvalidConfiguration_minInstancesGreaterThanMaxInstances() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(2)
                .minInstances(3);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Min instances cannot exceed max instances.", thrown.getMessage());
    }

    @Test
    void testBuildInvalidConfiguration_missingRequiredFields() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Environment and region must be set before building.", thrown.getMessage());
    }
}
