package com.radicalninja.pizzazz.ui;

import android.util.Log;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.render.TextRenderer;
import com.radicalninja.pizzazz.util.BitmapCanvas;
import com.radicalninja.pizzazz.util.Focusable;
import com.radicalninja.pizzazz.util.Fonts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends AbstractWindow implements Focusable {

    private final static String TAG = MenuWindow.class.getSimpleName();

    private final TextRenderer titleRenderer;
    private final TextRenderer itemRenderer;

    private boolean outlined = false;
    private int position = -1;
    private List<MenuItem> items = new ArrayList<>();

    public MenuWindow(String title) {
        super(title);
        titleRenderer = new TextRenderer(Fonts.CHRONO_TYPE.typeface());
        itemRenderer = new TextRenderer(Fonts.SUPER_MARIO_WORLD.typeface());

        titleRenderer.setTextSize(16);
        itemRenderer.setTextSize(8);
        itemRenderer.setMargin(1);
        position = 0;
    }

    public void addMenuItem(final String title, final MenuItem.Callback callback) {
        addMenuItem(new MenuItem(title, callback));
    }

    public void addMenuItem(final MenuItem item) {
        items.add(item);
    }

    @Override
    public void onFocused() {
        outlined = false;
        if (position < 0) {
            position = 0;
        }
    }

    @Override
    public void onUnfocused() {
        outlined = true;
    }

    @Override
    public void refresh(AbstractScreen screen) {
        final BitmapCanvas canvas = screen.canvas();

        int y = (int) titleRenderer.getTextSize();
        titleRenderer.setText(getTitle());
        titleRenderer.setStartPoint(0, y);
        titleRenderer.render(canvas);

        for (int i = 0; i < items.size(); i++) {
            final MenuItem item = items.get(i);
            itemRenderer.setHighlighted(i == position);
            y += itemRenderer.getRenderedHeight();
            itemRenderer.setText(item.getTitle());
            itemRenderer.setStartPoint(0, y);
            itemRenderer.render(canvas);
        }

        try {
            screen.drawBitmap(canvas.getBitmap(), 0, 0);
        } catch (IOException e) {
            Log.e(TAG, "Error drawing bitmap to screen!", e);
        }
        canvas.recycle();
    }
}
