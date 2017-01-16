package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Color;

import com.radicalninja.pizzazz.util.Margin;

public abstract class AbstractRenderer {

    private final static int COLOR_FILL = Color.WHITE;
    private final static int COLOR_EMPTY = Color.BLACK;

    private boolean invertColors = false;
    private Margin margin;

    public AbstractRenderer() {
        //
    }

    public boolean isInvertColors() {
        return invertColors;
    }

    public void setInvertColors(boolean invertColors) {
        this.invertColors = invertColors;
    }

    public int getColorFill() {
        return invertColors ? COLOR_EMPTY : COLOR_FILL;
    }

    public int getColorEmpty() {
        return invertColors ? COLOR_FILL : COLOR_EMPTY;
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(final int left, final int top, final int right, final int bottom) {
        margin.left = left;
        margin.top = top;
        margin.right = right;
        margin.bottom = bottom;
    }

    abstract void render(final Canvas canvas);

}
