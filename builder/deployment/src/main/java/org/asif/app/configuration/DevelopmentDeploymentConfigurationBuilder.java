package org.asif.app.configuration;

import org.asif.app.components.*;

import java.util.List;

public class DevelopmentDeploymentConfigurationBuilder implements DeploymentConfigurationBuilder {

    // Required fields
    private DeploymentEnvironment environment;
    private Region region;

    // Optional fields with default values
    private int maxInstances = 1;
    private int minInstances = 1;
    private boolean autoScalingEnabled = false;
    private NetworkPolicy networkPolicy = NetworkPolicy.DEFAULT;
    private List<LoggingLevel> loggingLevels = List.of(
            LoggingLevel.DEBUG, LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR);
    private StorageType storageType = StorageType.STANDARD;

    @Override
    public DeploymentConfigurationBuilder environment(DeploymentEnvironment environment) {
        if (environment == null) {
            throw new IllegalArgumentException("Environment must not be null.");
        }

        this.environment = environment;

        return this;
    }

    @Override
    public DeploymentConfigurationBuilder region(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }

        this.region = region;

        return this;
    }

    @Override
    public DeploymentConfigurationBuilder maxInstances(int maxInstances) {
        if (maxInstances < 1) {
            throw new IllegalArgumentException("Max instances must be at least 1.");
        }

        this.maxInstances = maxInstances;

        return this;
    }

    @Override
    public DeploymentConfigurationBuilder minInstances(int minInstances) {
        if (minInstances < 1) {
            throw new IllegalArgumentException("Min instances must be at least 1.");
        }

        this.minInstances = minInstances;

        return this;
    }

    @Override
    public DeploymentConfigurationBuilder autoScalingEnabled(boolean enabled) {
        this.autoScalingEnabled = enabled;
        return this;
    }

    @Override
    public DeploymentConfigurationBuilder networkPolicy(NetworkPolicy policy) {
        this.networkPolicy = policy;
        return this;
    }

    @Override
    public DeploymentConfigurationBuilder loggingLevels(List<LoggingLevel> loggingLevels) {
        this.loggingLevels = loggingLevels;
        return this;
    }

    @Override
    public DeploymentConfigurationBuilder storageType(StorageType storageType) {
        this.storageType = storageType;
        return this;
    }

    @Override
    public DeploymentConfiguration build() {
        if (minInstances > maxInstances) {
            throw new IllegalStateException("Min instances cannot exceed max instances.");
        }

        if (environment == null || region == null) {
            throw new IllegalStateException("Environment and region must be set before building.");
        }

        return new DeploymentConfiguration(
                environment,
                region,
                maxInstances,
                minInstances,
                autoScalingEnabled,
                networkPolicy,
                loggingLevels,
                storageType
        );
    }
}
