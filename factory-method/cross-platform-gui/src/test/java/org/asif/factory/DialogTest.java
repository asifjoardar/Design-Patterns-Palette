package org.asif.factory;

import org.asif.buttons.Button;
import org.asif.buttons.HtmlButton;
import org.asif.buttons.RecordingButton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Factory Method template: {@link Dialog#renderWindow()} works only
 * with the product returned by the overridable {@link Dialog#createButton()}.
 *
 * <p>WindowsDialog/WindowsButton are intentionally excluded: WindowsButton
 * builds Swing components ({@code new JFrame()} throws HeadlessException in CI)
 * and calls {@code System.exit(0)} on click, so it cannot be unit tested here.
 */
class DialogTest {

    @Test
    void renderWindowRendersTheFactoryMethodProduct() {
        RecordingButton product = new RecordingButton();
        Dialog dialog = new Dialog() {
            @Override
            public Button createButton() {
                return product;
            }
        };

        dialog.renderWindow();

        assertTrue(product.isRendered(),
                "renderWindow() should render the button produced by createButton()");
    }

    @Test
    void htmlDialogCreatesHtmlButton() {
        Dialog dialog = new HtmlDialog();

        assertInstanceOf(HtmlButton.class, dialog.createButton());
    }

    @Test
    void htmlDialogRenderWindowDoesNotThrow() {
        assertDoesNotThrow(() -> new HtmlDialog().renderWindow());
    }
}
