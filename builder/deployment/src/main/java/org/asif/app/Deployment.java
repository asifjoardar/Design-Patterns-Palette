package org.asif.app;

import org.asif.app.configuration.DeploymentConfiguration;

public class Deployment {
    private final DeploymentConfiguration configuration;

    public Deployment(DeploymentConfiguration configuration) {
        this.configuration = configuration;
    }

    public void deploy() {
        System.out.println("Deploying application with configuration: " + configuration);
    }
}
