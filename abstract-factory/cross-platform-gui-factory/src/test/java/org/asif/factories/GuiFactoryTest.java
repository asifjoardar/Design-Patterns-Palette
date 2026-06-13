package org.asif.factories;

import org.asif.buttons.LinuxButton;
import org.asif.buttons.MacOSButton;
import org.asif.buttons.WindowsButton;
import org.asif.checkboxes.LinuxCheckbox;
import org.asif.checkboxes.MacOSCheckbox;
import org.asif.checkboxes.WindowsCheckbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Each concrete factory must produce a matching family of products — the core
 * guarantee of the Abstract Factory pattern.
 */
class GuiFactoryTest {

    @Test
    void windowsFactoryCreatesWindowsFamily() {
        GUIFactory factory = new WindowsFactory();

        assertInstanceOf(WindowsButton.class, factory.createButton());
        assertInstanceOf(WindowsCheckbox.class, factory.createCheckbox());
    }

    @Test
    void linuxFactoryCreatesLinuxFamily() {
        GUIFactory factory = new LinuxFactory();

        assertInstanceOf(LinuxButton.class, factory.createButton());
        assertInstanceOf(LinuxCheckbox.class, factory.createCheckbox());
    }

    @Test
    void macOsFactoryCreatesMacOsFamily() {
        GUIFactory factory = new MacOSFactory();

        assertInstanceOf(MacOSButton.class, factory.createButton());
        assertInstanceOf(MacOSCheckbox.class, factory.createCheckbox());
    }
}
