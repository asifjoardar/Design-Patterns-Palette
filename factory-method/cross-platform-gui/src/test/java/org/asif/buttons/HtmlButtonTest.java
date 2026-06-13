package org.asif.buttons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HtmlButtonTest {

    @Test
    void renderAndClickDoNotThrow() {
        HtmlButton button = new HtmlButton();

        assertDoesNotThrow(() -> {
            button.render();
            button.onClick();
        });
    }
}
