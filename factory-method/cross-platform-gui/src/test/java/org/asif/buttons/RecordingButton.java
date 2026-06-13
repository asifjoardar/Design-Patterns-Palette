package org.asif.buttons;

/**
 * Hand-written test double used to verify that a {@code Dialog} renders the
 * product returned by its factory method.
 */
public class RecordingButton implements Button {
    private boolean rendered;
    private boolean clicked;

    @Override
    public void render() {
        rendered = true;
    }

    @Override
    public void onClick() {
        clicked = true;
    }

    public boolean isRendered() {
        return rendered;
    }

    public boolean isClicked() {
        return clicked;
    }
}
