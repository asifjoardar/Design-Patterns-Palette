package org.asif.app.configuration;

import org.asif.app.components.*;

import java.util.List;

public class DeploymentDirector {

    private final DeploymentConfigurationBuilder builder;

    public DeploymentDirector(DeploymentConfigurationBuilder builder) {
        this.builder = builder;
    }

    public DeploymentConfiguration constructDevelopmentDeploymentConfiguration() {
        builder.environment(DeploymentEnvironment.DEVELOPMENT)
                .region(Region.EU_WEST_1);

        return builder.build();
    }

    public DeploymentConfiguration constructStagingDeploymentConfiguration() {
        builder.environment(DeploymentEnvironment.STAGING)
                .region(Region.US_EAST_1)
                .maxInstances(2)
                .minInstances(1)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.RESTRICTED)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.ERROR))
                .storageType(StorageType.PREMIUM);

        return builder.build();
    }

    public DeploymentConfiguration constructProductionDeploymentConfiguration() {
        builder.environment(DeploymentEnvironment.PRODUCTION)
                .region(Region.US_WEST_2)
                .maxInstances(10)
                .minInstances(5)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.ARCHIVE);

        return builder.build();
    }
}
