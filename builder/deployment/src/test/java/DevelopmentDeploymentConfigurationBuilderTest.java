import org.asif.app.components.*;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.asif.app.configuration.DevelopmentDeploymentConfigurationBuilder;
import org.asif.app.utils.DeploymentConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class DevelopmentDeploymentConfigurationBuilderTest {

    @Test
    void testBuildValidConfiguration() {
        DeploymentConfigurationBuilder builder = new DevelopmentDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_DEVELOPMENT)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_DEVELOPMENT)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(
                        LoggingLevel.DEBUG,
                        LoggingLevel.INFO,
                        LoggingLevel.WARN,
                        LoggingLevel.ERROR))
                .storageType(StorageType.STANDARD);

        DeploymentConfiguration config = builder.build();

        assertNotNull(config);
        assertEquals(DeploymentEnvironment.DEVELOPMENT, config.getEnvironment());
        assertEquals(Region.US_EAST_1, config.getRegion());
        assertEquals(DeploymentConstants.DEFAULT_MAX_INSTANCES_DEVELOPMENT, config.getMaxInstances());
        assertEquals(DeploymentConstants.DEFAULT_MIN_INSTANCES_DEVELOPMENT, config.getMinInstances());
        assertTrue(config.isAutoScalingEnabled());
        assertEquals(NetworkPolicy.OPEN, config.getNetworkPolicy());
        assertEquals(List.of(
                LoggingLevel.DEBUG,
                LoggingLevel.INFO,
                LoggingLevel.WARN,
                LoggingLevel.ERROR), config.getLoggingLevels());
        assertEquals(StorageType.STANDARD, config.getStorageType());
    }

    @Test
    void testBuildInvalidConfigurationWithMinInstancesGreaterThanMaxInstances() {
        DeploymentConfigurationBuilder builder = new DevelopmentDeploymentConfigurationBuilder();
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .maxInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_DEVELOPMENT)
                .minInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_DEVELOPMENT);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Min instances cannot exceed max instances.", thrown.getMessage());
    }

    @Test
    void testBuildInvalidConfigurationWithMissingRequiredFields() {
        DeploymentConfigurationBuilder builder = new DevelopmentDeploymentConfigurationBuilder();

        IllegalStateException thrown = assertThrows(IllegalStateException.class, builder::build);
        assertEquals("Environment and region must be set before building.", thrown.getMessage());
    }
}
