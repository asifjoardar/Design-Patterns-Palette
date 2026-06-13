package org.asif.app;

import org.asif.app.components.DeploymentEnvironment;
import org.asif.app.components.Region;
import org.asif.app.configuration.ConcreteDeploymentConfigurationBuilder;
import org.asif.app.configuration.DeploymentConfiguration;
import org.asif.app.configuration.DeploymentConfigurationBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Each builder step validates its input independently, and build() enforces the
 * required fields.
 */
class ConcreteDeploymentConfigurationBuilderValidationTest {

    private static final int INVALID_INSTANCES = 0;

    private static DeploymentConfigurationBuilder builder() {
        return new ConcreteDeploymentConfigurationBuilder();
    }

    @Test
    void nullEnvironmentIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> builder().environment(null));
    }

    @Test
    void nullRegionIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> builder().region(null));
    }

    @Test
    void maxInstancesBelowOneIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> builder().maxInstances(INVALID_INSTANCES));
    }

    @Test
    void minInstancesBelowOneIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> builder().minInstances(INVALID_INSTANCES));
    }

    @Test
    void buildWithoutEnvironmentIsRejected() {
        DeploymentConfigurationBuilder builder = builder().region(Region.US_EAST_1);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void buildWithoutRegionIsRejected() {
        DeploymentConfigurationBuilder builder = builder()
                .environment(DeploymentEnvironment.DEVELOPMENT);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void buildSucceedsWithBothRequiredFields() {
        DeploymentConfiguration config = builder()
                .environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.US_EAST_1)
                .build();

        assertNotNull(config);
    }
}
