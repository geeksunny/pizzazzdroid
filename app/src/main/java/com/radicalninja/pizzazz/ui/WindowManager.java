package com.radicalninja.pizzazz.ui;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.input.ButtonManager;
import com.radicalninja.pizzazz.input.MultiplexButtonController;

public class WindowManager extends MultiplexButtonController {

    private final AbstractScreen leftScreen, rightScreen;

    private AbstractScreen focusedScreen;

    public WindowManager(final AbstractScreen leftScreen, final AbstractScreen rightScreen) {
        this.leftScreen = leftScreen;
        this.rightScreen = rightScreen;
        ButtonManager.INSTANCE.setButtonController(this);
    }

    public void focusLeft() {
        focusScreen(leftScreen);
    }

    public void focusRight() {
        focusScreen(rightScreen);
    }

    private void focusScreen(final AbstractScreen screen) {
        if (focusedScreen == screen) {
            return;
        }
        focusedScreen = screen;
    }

    public void setLeftWindow(AbstractWindow window) {
        leftScreen.setWindow(window);
        if (null == focusedScreen) {
            focusLeft();
        }
    }

    public void setRightWindow(AbstractWindow window) {
        rightScreen.setWindow(window);
        if (null == focusedScreen) {
            focusRight();
        }
    }
}
