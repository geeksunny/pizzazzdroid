package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.radicalninja.pizzazz.util.Font;
import com.radicalninja.pizzazz.util.TextAlignment;

public class TextRenderer extends AbstractRenderer {

    private final Font font;

    private int width = 70; // TODO: Move this into AbstractRenderer? maybe min/max width/height?
    private TextAlignment alignment = TextAlignment.LEFT;
    private Point xyStart = new Point();
    private String text;

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

    public Point getStartPoint() {
        return xyStart;
    }

    public void setStartPoint(final int x, final int y) {
        xyStart.set(x, y);
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    void render(Canvas canvas) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        final Paint paint = new Paint();
        paint.setTypeface(font.getTypeface());
        paint.setTextSize(font.getTextSize());
        canvas.drawText(text, xyStart.x, xyStart.y, paint);
    }

}
