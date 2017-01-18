package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.radicalninja.pizzazz.util.Font;
import com.radicalninja.pizzazz.util.Margin;
import com.radicalninja.pizzazz.util.TextAlignment;

public class TextRenderer extends AbstractRenderer {

    private final Font font;

    private int width = 70; // TODO: Move this into AbstractRenderer? maybe min/max width/height?
    private TextAlignment alignment = TextAlignment.LEFT;
    private String text;
    private boolean highlighted;

    public TextRenderer(final Font font) {
        this.font = font;
    }

    public TextRenderer(final Typeface typeface) {
        font = new Font(typeface);
    }

    public TextRenderer(final Typeface typeface, final float size) {
        font = new Font(typeface, size);
    }

    public TextAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
    }

    public Font getFont() {
        return font;
    }

    public void setTypeface(final Typeface typeface) {
        font.setTypeface(typeface);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public float getRenderedWidth() {
        final float renderedWidth = width + getMargin().left + getMargin().right;
        return (renderedWidth > width) ? (float) width : renderedWidth;
    }

    @Override
    public float getRenderedHeight() {
        return getTextSize() + getMargin().top + getMargin().bottom;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public float getTextSize() {
        return font.getTextSize();
    }

    public void setTextSize(final float textSize) {
        font.setTextSize(textSize);
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public void render(Canvas canvas) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        final Paint paint = new Paint();
        paint.setColor(getColorFill());
        final Margin margin = getMargin();
        final Point xy = getStartPoint();
        if (highlighted) {
            final float left = (float) xy.x;
            final float top = xy.y - getTextSize();
            final float right = (float) xy.x + width + margin.left + margin.right;
            final float bottom = (float) xy.y + margin.top + margin.bottom;
            canvas.drawRect(left, top, right, bottom, paint);
            paint.setColor(getColorEmpty());
        }
        paint.setTypeface(font.getTypeface());
        paint.setTextSize(font.getTextSize());
        final float startX = xy.x + margin.top;
        final float startY = xy.y + margin.left;
        canvas.drawText(text, startX, startY, paint);
    }

}
