package com.radicalninja.pizzazz.display;

import android.graphics.Bitmap;

public abstract class AbstractScreen {

    private boolean focused;

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    abstract void drawBitmap(final Bitmap bitmap);

    abstract void clearScreen();

}
