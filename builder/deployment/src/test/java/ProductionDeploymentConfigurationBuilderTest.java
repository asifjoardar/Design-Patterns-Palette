import org.asif.app.components.*;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.asif.app.configuration.ProductionDeploymentConfigurationBuilder;
import org.asif.app.utils.DeploymentConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductionDeploymentConfigurationBuilderTest {

    @Test
    void testBuildValidConfiguration() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.ERROR))
                .storageType(StorageType.PREMIUM);

        DeploymentConfiguration config = builder.build();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.OPEN, config.getNetworkPolicy());
        assertEquals(List.of(LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.PREMIUM, config.getStorageType());
    }

    @Test
    void testBuildInvalidConfigurationWithMinInstancesGreaterThanMaxInstances() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION)
                .minInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Min instances cannot exceed max instances.", thrown.getMessage());
    }

    @Test
    void testBuildInvalidConfigurationWithMissingRequiredFields() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Environment and region must be set before building.", thrown.getMessage());
    }
}
