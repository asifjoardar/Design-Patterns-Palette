package org.asif.app;

import org.asif.app.components.*;
import org.asif.app.configuration.*;
import org.asif.app.utils.DeploymentConstants;

import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void deployWithDevelopmentConfiguration() {
        DeploymentConfigurationBuilder builder = new DevelopmentDeploymentConfigurationBuilder();
        DeploymentDirector director = new DeploymentDirector(builder);

        DeploymentConfiguration developmentDeploymentConfiguration =
                director.constructDevelopmentDeploymentConfiguration();

        Deployment deploymentForDevelopment = new Deployment(developmentDeploymentConfiguration);
        deploymentForDevelopment.deploy();
    }

    public static void deployWithStagingConfiguration() {
        DeploymentConfigurationBuilder builder = new StagingDeploymentConfigurationBuilder();
        DeploymentDirector director = new DeploymentDirector(builder);

        DeploymentConfiguration stagingDeploymentConfiguration =
                director.constructStagingDeploymentConfiguration();

        Deployment deploymentForStaging = new Deployment(stagingDeploymentConfiguration);
        deploymentForStaging.deploy();
    }

    public static void deployWithProductionConfiguration() {
        DeploymentConfigurationBuilder builder = new ProductionDeploymentConfigurationBuilder();
        DeploymentDirector director = new DeploymentDirector(builder);

        DeploymentConfiguration productionDeploymentConfiguration =
                director.constructProductionDeploymentConfiguration();

        Deployment deploymentForProduction = new Deployment(productionDeploymentConfiguration);
        deploymentForProduction.deploy();
    }

    public static void deployWithCustomConfiguration() {
        ProductionDeploymentConfigurationBuilder productionDeploymentConfigurationBuilder =
                new ProductionDeploymentConfigurationBuilder();

        productionDeploymentConfigurationBuilder
                .environment(DeploymentEnvironment.PRODUCTION)
                .region(Region.US_WEST_2)
                .maxInstances(DeploymentConstants.DEFAULT_MAX_INSTANCES_PRODUCTION)
                .minInstances(DeploymentConstants.DEFAULT_MIN_INSTANCES_PRODUCTION)
                .autoScalingEnabled(true)
                .networkPolicy(NetworkPolicy.OPEN)
                .loggingLevels(List.of(LoggingLevel.INFO, LoggingLevel.WARN, LoggingLevel.ERROR))
                .storageType(StorageType.ARCHIVE);

        DeploymentConfiguration configuration2 = productionDeploymentConfigurationBuilder.build();
        Deployment deployment = new Deployment(configuration2);
        deployment.deploy();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the deployment type:");
            System.out.println("1. Development");
            System.out.println("2. Staging");
            System.out.println("3. Production");
            System.out.println("4. Custom");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case DeploymentConstants.OPTION_DEVELOPMENT -> deployWithDevelopmentConfiguration();
                case DeploymentConstants.OPTION_STAGING -> deployWithStagingConfiguration();
                case DeploymentConstants.OPTION_PRODUCTION -> deployWithProductionConfiguration();
                case DeploymentConstants.OPTION_CUSTOM_CONFIGURATION -> deployWithCustomConfiguration();
                case DeploymentConstants.OPTION_EXIT -> {
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Deployment completed. \uD83D\uDE80");
        }
    }
}
