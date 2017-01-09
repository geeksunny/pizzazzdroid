package com.radicalninja.pizzazz.display;

import com.radicalninja.pizzazz.ui.AbstractWindow;

public abstract class AbstractScreen {

    private boolean focused;
    private AbstractWindow window;

    public void setWindow(final AbstractWindow window) {
        this.window = window;
    }

    // TODO: write methods for drawing shapes/text/etc

    abstract void drawWindow();

    abstract void clearScreen();

}
