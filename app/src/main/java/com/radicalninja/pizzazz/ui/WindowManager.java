package com.radicalninja.pizzazz.ui;

import android.util.Log;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.input.ButtonManager;
import com.radicalninja.pizzazz.input.MultiplexButtonController;
import com.radicalninja.pizzazz.util.Focusable;

import java.io.IOException;
import java.util.LinkedList;

public class WindowManager extends MultiplexButtonController {

    private static final String TAG = WindowManager.class.getSimpleName();

    // Displays are presently handled in a single horizontal line. TODO: Add DisplayLayouts / DisplayConfigurations
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
        if (null != focusedDisplay) {
            focusedDisplay.setFocused(false);
        }
        focusedDisplay = display;
        focusedDisplay.setFocused(true);
    }

    private void refreshDisplays() {
        for (final Display display : displays) {
            display.refresh();
        }
    }

    public void start() {
        // todo: no displays? throw exception
        refreshDisplays();
    }

    public void stop() {
        for (final Display display : displays) {
            try {
                display.screen.clearScreen();
            } catch (IOException e) {
                Log.e(TAG, "Encountered an error trying to clear the display.", e);
            }
        }
    }

    public void cleanup() {
        // todo: close screen and gpio objects?
    }

    private class Display {

        final AbstractScreen screen;
        final History history = new History();

        AbstractWindow currentWindow;
        boolean isFocused;

        public Display(final AbstractScreen screen, final AbstractWindow currentWindow) {
            this.screen = screen;
            this.currentWindow = currentWindow;
            openWindow(currentWindow);
        }

        public void openWindow(final AbstractWindow window) {
            // TODO: Add logic for using Closeable interface
            // todo: focus new window?
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

        public void refresh() {
            currentWindow.refresh(screen);
        }
    }

}
