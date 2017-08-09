package com.radicalninja.pizzazz.ui;

import android.util.Log;

import com.radicalninja.pizzazz.display.Screen;
import com.radicalninja.pizzazz.input.ButtonController;
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

    public void registerDisplay(final Screen screen, final Window firstWindow) {
        registerDisplay(screen, firstWindow, false);
    }

    public void registerDisplay(
            final Screen screen, final Window firstWindow, final boolean addToLeft) {
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
        // TODO: Add AsyncTask / TreadPool for handling refresh
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

        final Screen screen;
        final History history = new History();

        Window currentWindow;
        boolean isFocused;

        public Display(final Screen screen, final Window currentWindow) {
            this.screen = screen;
            this.currentWindow = currentWindow;
            openWindow(currentWindow);
        }

        public ButtonController[] getButtonControllers() {
            return currentWindow.getButtonControllers();
        }

        public void openWindow(final Window window) {
            window.open(screen);
            // todo: focus new window?
        }

        public boolean isFocused() {
            return isFocused;
        }

        public void setFocused(boolean focused) {
            if (isFocused == focused) {
                return;
            }
            isFocused = focused;
            if (currentWindow instanceof Focusable) {
                final Focusable window = (Focusable) currentWindow;
                if (focused) {
                    window.onFocused();
                    registerControllers(getButtonControllers());
                } else {
                    window.onUnfocused();
                    unregisterControllers(getButtonControllers());
                }
            }
        }

        public void refresh() {
            currentWindow.refresh(screen);
        }
    }

}
