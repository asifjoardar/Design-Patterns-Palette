package org.asif;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ConfigurationManagerTest {

    @Test
    void getInstanceAlwaysReturnsTheSameObject() {
        ConfigurationManager first = ConfigurationManager.getInstance();
        ConfigurationManager second = ConfigurationManager.getInstance();

        assertSame(first, second, "Singleton must hand back one shared instance");
    }

    @Test
    void loadsSettingsFromTheConfigFile() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        assertEquals("Design Patterns Palette", config.get("app.name"));
    }
}
