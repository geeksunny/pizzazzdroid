package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;

import com.radicalninja.pizzazz.util.Font;

import java.util.LinkedList;
import java.util.List;

public class ListRenderer extends TextRenderer {

    private int position = -1;
    private List<String> strings = new LinkedList<>();
    private Point xy = new Point();

    public ListRenderer(Font font) {
        super(font);
    }

    public ListRenderer(Typeface typeface) {
        super(typeface);
    }

    public ListRenderer(Typeface typeface, float size) {
        super(typeface, size);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addString(final String string) {
        strings.add(string);
    }

    public void setStrings(final List<String> strings) {
        this.strings.clear();
        for (final String string : strings) {
            this.strings.add(string);
        }
    }

    @Override
    public void setStartPoint(int x, int y) {
        xy.set(x, y);
    }

    @Override
    public float getRenderedHeight() {
        return 0;
    }

    @Override
    public void render(Canvas canvas) {
        int y = xy.y;
        for (int i = 0; i < strings.size(); i++) {
            y += super.getRenderedHeight();
            final String string = strings.get(i);
            super.setHighlighted(i == position);
            super.setText(string);
            super.setStartPoint(xy.x, y);
            super.render(canvas);
        }
    }

}
