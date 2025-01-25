package org.asif.app.configuration;

import org.asif.app.components.*;
import org.asif.app.utils.DeploymentConstants;

import java.util.List;

public class DeploymentConfigurationDirector {

    private final DeploymentConfigurationBuilder builder;

    public DeploymentConfigurationDirector(DeploymentConfigurationBuilder builder) {
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
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_STAGING)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_STAGING)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.RESTRICTED)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.ERROR))
                .storageType(StorageType.PREMIUM);

        return builder.build();
    }

    public DeploymentConfiguration constructProductionDeploymentConfiguration() {
        builder.environment(DeploymentEnvironment.PRODUCTION)
                .region(Region.US_WEST_2)
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.ARCHIVE);

        return builder.build();
    }
}
