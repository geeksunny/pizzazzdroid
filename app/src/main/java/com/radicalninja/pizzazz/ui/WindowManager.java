package com.radicalninja.pizzazz.ui;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.input.ButtonManager;
import com.radicalninja.pizzazz.input.MultiplexButtonController;
import com.radicalninja.pizzazz.util.Focusable;

import java.util.LinkedList;

public class WindowManager extends MultiplexButtonController {

    // Displays are presently handled in a single horizontal line. TODO: Add DisplayConfigurations
    private final LinkedList<Display> displays = new LinkedList<>();

    private Display focusedDisplay;

    public WindowManager() {
        ButtonManager.INSTANCE.setButtonController(this);
    }

    public void registerDisplay(final AbstractScreen screen, final AbstractWindow firstWindow) {
        registerDisplay(screen, firstWindow, false);
    }

    public void registerDisplay(
            final AbstractScreen screen, final AbstractWindow firstWindow, final boolean addToLeft) {
        final Display display = new Display(screen, firstWindow);
        if (addToLeft) {
            displays.push(display);
        } else {
            displays.add(display);
        }
        if (null == focusedDisplay) {
            focusedDisplay = display;
        }
    }

    private void moveFocusLeft() {
        moveFocus(-1);
    }

    private void moveFocusRight() {
        moveFocus(+1);
    }

    private void moveFocus(final int offset) {
        if (displays.size() <= 1) {
            return;
        }
        try {
            final int index = displays.indexOf(focusedDisplay);
            if (index == -1) {
                return;
            }
            final Display nextDisplay = displays.get(index + offset);
            if (null != nextDisplay) {
                focusDisplay(nextDisplay);
            }
        } catch (IndexOutOfBoundsException ignored) {
            // No screen to focus
        }
    }

    private void focusDisplay(final Display display) {
        if (focusedDisplay == display) {
            return;
        }
        // TODO: Unfocus focusedDisplay
        focusedDisplay.setFocused(false);
        // TODO: focusedDisplay = display
        focusedDisplay = display;
        // TODO: Focus focusedDisplay
        focusedDisplay.setFocused(true);
    }

    public void start() {

    }

    public void stop() {

    }

    public void cleanup() {

    }

    private class Display {

        // TODO: Add logic for using Closeable interface
        final AbstractScreen screen;
        final History history = new History();

        AbstractWindow currentWindow;
        boolean isFocused;

        public Display(final AbstractScreen screen, final AbstractWindow currentWindow) {
            this.screen = screen;
            this.currentWindow = currentWindow;
        }

        public boolean isFocused() {
            return isFocused;
        }

        public void setFocused(boolean focused) {
            isFocused = focused;
            if (currentWindow instanceof Focusable) {
                final Focusable window = (Focusable) currentWindow;
                if (focused) {
                    window.onFocused();
                } else {
                    window.onUnfocused();
                }
            }
        }
    }

}
