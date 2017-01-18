package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.radicalninja.pizzazz.util.Margin;

public class LineRenderer extends AbstractRenderer {

    // TODO: Move height/width to interface?
    private int height;
    private int width = 70;

    // TODO: value constructors

    public LineRenderer() {
        height = 1;
        setMargin(0, 0, 0, 1);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public float getRenderedHeight() {
        return height + getMargin().top + getMargin().bottom;
    }

    @Override
    public float getRenderedWidth() {
        return width + getMargin().left + getMargin().right;
    }

    @Override
    public void render(Canvas canvas) {
        final Paint paint = new Paint();
        paint.setColor(getColorFill());
        final Point xy = getStartPoint();
        final Margin margin = getMargin();
        final float left = xy.x + margin.left;
        final float top = xy.y + margin.top;
        final float right = xy.x + width + margin.left;
        final float bottom = xy.y + margin.top + height;
        canvas.drawRect(left, top, right, bottom, paint);
    }

}
