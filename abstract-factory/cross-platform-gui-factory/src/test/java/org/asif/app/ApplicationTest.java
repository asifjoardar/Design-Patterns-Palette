package org.asif.app;

import org.asif.factories.LinuxFactory;
import org.asif.factories.MacOSFactory;
import org.asif.factories.WindowsFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * The client works with any factory through the abstract interface; building
 * and painting a family should succeed regardless of the concrete factory.
 */
class ApplicationTest {

    @Test
    void applicationPaintsWindowsFamily() {
        Application application = new Application(new WindowsFactory());

        assertDoesNotThrow(application::paint);
    }

    @Test
    void applicationPaintsLinuxFamily() {
        Application application = new Application(new LinuxFactory());

        assertDoesNotThrow(application::paint);
    }

    @Test
    void applicationPaintsMacOsFamily() {
        Application application = new Application(new MacOSFactory());

        assertDoesNotThrow(application::paint);
    }
}
