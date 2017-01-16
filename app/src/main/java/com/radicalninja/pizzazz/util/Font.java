package com.radicalninja.pizzazz.util;

import android.graphics.Paint;
import android.graphics.Typeface;

public class Font {

    public static final int DEFAULT_SIZE = 10;

    private final Paint paint;

    public Font(final Typeface typeface) {
        this(typeface, DEFAULT_SIZE);
    }

    public Font(final Typeface typeface, final float textSize) {
        paint = new Paint();
        setTypeface(typeface);
        setTextSize(textSize);
    }

    public Typeface getTypeface() {
        return paint.getTypeface();
    }

    public void setTypeface(final Typeface typeface) {
        paint.setTypeface(typeface);
    }

    public float getTextSize() {
        return paint.getTextSize();
    }

    public void setTextSize(final float textSize) {
        paint.setTextSize(textSize);
    }

    public float getTextWidth(final String text) {
        return paint.measureText(text);
    }

}
