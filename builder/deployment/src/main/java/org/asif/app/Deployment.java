package org.asif.app;

import org.asif.app.configuration.DeploymentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deployment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Deployment.class);

    private final DeploymentConfiguration configuration;

    public Deployment(DeploymentConfiguration configuration) {
        this.configuration = configuration;
    }

    public void deploy() {
        LOGGER.info("Deploying application with configuration: {}", configuration);
    }
}
