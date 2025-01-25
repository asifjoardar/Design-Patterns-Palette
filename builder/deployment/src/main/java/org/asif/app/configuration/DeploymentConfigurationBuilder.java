package org.asif.app.configuration;

import org.asif.app.components.*;

import java.util.List;

public interface DeploymentConfigurationBuilder {
    DeploymentConfigurationBuilder environment(DeploymentEnvironment environment);
    DeploymentConfigurationBuilder region(Region region);
    DeploymentConfigurationBuilder maxInstances(int maxInstances);
    DeploymentConfigurationBuilder minInstances(int minInstances);
    DeploymentConfigurationBuilder autoScalingEnabled(boolean enabled);
    DeploymentConfigurationBuilder networkPolicy(NetworkPolicy policy);
    DeploymentConfigurationBuilder loggingLevels(List<LoggingLevel> loggingLevels);
    DeploymentConfigurationBuilder storageType(StorageType storageType);
    DeploymentConfiguration build();
}
