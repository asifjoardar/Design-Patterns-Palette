package org.asif.app.utils;

public final class DeploymentConstants {

    public static final int DEFAULT_MIN_INSTANCES = 1;
    public static final int DEFAULT_MAX_INSTANCES = 1;

    public static final int DEFAULT_MIN_INSTANCES_DEVELOPMENT = 1;
    public static final int DEFAULT_MAX_INSTANCES_DEVELOPMENT = 2;

    public static final int DEFAULT_MIN_INSTANCES_STAGING = 1;
    public static final int DEFAULT_MAX_INSTANCES_STAGING = 3;

    public static final int DEFAULT_MIN_INSTANCES_PRODUCTION = 5;
    public static final int DEFAULT_MAX_INSTANCES_PRODUCTION = 10;

    public static final int OPTION_DEVELOPMENT = 1;
    public static final int OPTION_STAGING = 2;
    public static final int OPTION_PRODUCTION = 3;
    public static final int OPTION_CUSTOM_CONFIGURATION = 4;
    public static final int OPTION_EXIT = 5;


    private DeploymentConstants() {
        throw new UnsupportedOperationException(
                "The utility class DeploymentConstants cannot be instantiated"
        );
    }
}
