package com.radicalninja.pizzazz.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;

public class Font {

    public static final int DEFAULT_SIZE = 10;

    private String filename;
    private int size = DEFAULT_SIZE;
    private Typeface typeface = Typeface.DEFAULT;
    private Paint paint;

    public Font() {
    }

    public Font(final String filename, final int size) {
        setFont(filename, size);
    }

    public Font(final Font other) {
        // TODO: deeper copy constructor?
        this.filename = other.filename;
        this.size = other.size;
        this.typeface = other.typeface;
        this.paint = other.paint;
    }

    private void initFont(final String filename) {
        if (TextUtils.isEmpty(filename)) {
            return;
        }
        // TODO: Check if file exists before creating typeface
        typeface = Typeface.createFromFile(filename);
        setupPaint();
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setFont(final String filename) {
        this.filename = filename;
        initFont(filename);
    }

    public void setFont(final String filename, final int size) {
        this.filename = filename;
        this.size = size;
        initFont(filename);
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        if (this.size == size) {
            return;
        }
        this.size = size;
        setupPaint();
    }

    private void setupPaint() {
        if (null == paint) {
            paint = new Paint();
        }
        paint.setTypeface(typeface);
        paint.setTextSize((float) size);    // TODO: dp<->pixel calculation required?
    }

}
