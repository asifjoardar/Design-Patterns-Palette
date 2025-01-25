package org.asif.app.configuration;

import org.asif.app.components.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DeploymentConfiguration {
    private final DeploymentEnvironment environment;
    private final Region region;
    private final int maxInstances;
    private final int minInstances;
    private final boolean autoScalingEnabled;
    private final NetworkPolicy networkPolicy;
    private final List<LoggingLevel> loggingLevels;
    private final StorageType storageType;

    // Package Private constructor
    DeploymentConfiguration(DeploymentEnvironment environment,
                            Region region,
                            int maxInstances,
                            int minInstances,
                            boolean autoScalingEnabled,
                            NetworkPolicy networkPolicy,
                            List<LoggingLevel> loggingLevels,
                            StorageType storageType) {
        this.environment = environment;
        this.region = region;
        this.maxInstances = maxInstances;
        this.minInstances = minInstances;
        this.autoScalingEnabled = autoScalingEnabled;
        this.networkPolicy = networkPolicy;
        this.loggingLevels = loggingLevels;
        this.storageType = storageType;
    }
}
