package com.radicalninja.pizzazz.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BitmapCanvas extends Canvas {

    private final Bitmap bitmap;

    public BitmapCanvas(Bitmap bitmap) {
        super(bitmap);
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void recycle() {
        bitmap.recycle();
    }

}
