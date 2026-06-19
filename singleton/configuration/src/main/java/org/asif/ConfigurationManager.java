package org.asif;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A thread-safe Singleton that loads application settings once and shares the
 * same instance across the whole application.
 *
 * <p>It uses the initialization-on-demand holder idiom: the single instance is
 * created lazily and safely the first time {@link #getInstance()} is called,
 * without any explicit synchronization. The JVM guarantees a nested class is
 * loaded only when it is first referenced, so the instance is built exactly
 * once even when many threads ask for it at the same time.</p>
 */
public final class ConfigurationManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);
    private static final String CONFIG_FILE = "application.properties";

    private final Properties settings = new Properties();

    private ConfigurationManager() {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + CONFIG_FILE + " on the classpath");
            }
            settings.load(input);
            LOGGER.info("Configuration loaded once from {}", CONFIG_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load configuration", e);
        }
    }

    private static final class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    /**
     * Returns the one and only ConfigurationManager instance.
     *
     * @return the shared instance
     */
    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Looks up a setting by its key.
     *
     * @param key the setting name, e.g. {@code "app.name"}
     * @return the setting value, or {@code null} if it is not defined
     */
    public String get(String key) {
        return settings.getProperty(key);
    }
}
