package com.radicalninja.pizzazz.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.io.IOException;

public abstract class AbstractScreen {

    public abstract void drawBitmap(final Bitmap bitmap, final int x, final int y) throws IOException;

    public abstract void clearScreen() throws IOException;

    public abstract int getWidth();
    public abstract int getHeight();

    public Canvas canvas() {
        final Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        return new Canvas(bitmap);
    }

}
