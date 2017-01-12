package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.radicalninja.pizzazz.util.Font;
import com.radicalninja.pizzazz.util.TextAlignment;

public class TextRenderer extends AbstractRenderer {

    private Font font;
    private int width = 70;
    private TextAlignment alignment = TextAlignment.LEFT;

    public TextRenderer() {
        font = new Font();
    }

    public TextRenderer(final Font font) {
        this.font = font;
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

    public void setFont(Font font) {
        this.font = font;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    void render(Canvas canvas) {
        final Paint paint = new Paint();
        paint.setTypeface(font.getTypeface());
    }

}
