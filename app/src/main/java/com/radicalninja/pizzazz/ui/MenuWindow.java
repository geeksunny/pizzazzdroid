package com.radicalninja.pizzazz.ui;

import android.util.Log;

import com.radicalninja.pizzazz.display.Screen;
import com.radicalninja.pizzazz.render.LineRenderer;
import com.radicalninja.pizzazz.render.ListRenderer;
import com.radicalninja.pizzazz.render.TextRenderer;
import com.radicalninja.pizzazz.util.BitmapCanvas;
import com.radicalninja.pizzazz.util.Focusable;
import com.radicalninja.pizzazz.util.Fonts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends Window implements Focusable {

    private final static String TAG = MenuWindow.class.getSimpleName();

    // TODO: Move titleRenderer, title rendering, line rendering code to AbstractWindow
    private final LineRenderer lineRenderer = new LineRenderer();
    private final TextRenderer titleRenderer;
    private final ListRenderer listRenderer;

    private boolean outlined = false;
    private List<MenuItem> items = new ArrayList<>();

    public MenuWindow(String title) {
        super(title);
        titleRenderer = new TextRenderer(Fonts.CHRONO_TYPE.typeface(), 12);
        listRenderer = new ListRenderer(Fonts.SUPER_MARIO_WORLD.typeface(), 7);

        titleRenderer.setMargin(0);
        listRenderer.setMargin(1);
        setPosition(1);
    }

    public int getPosition() {
        return listRenderer.getPosition();
    }

    public void setPosition(int position) {
        listRenderer.setPosition(position);
    }

    public void addMenuItem(final String title, final MenuItem.Callback callback) {
        addMenuItem(new MenuItem(title, callback));
    }

    public void addMenuItem(final MenuItem item) {
        items.add(item);
        listRenderer.addString(item.getTitle());
    }

    @Override
    public void setWidth(int width) {
        titleRenderer.setWidth(width);
        listRenderer.setWidth(width);
        lineRenderer.setWidth(width);
    }

    @Override
    public void onFocused() {
        outlined = false;
        if (getPosition() < 0) {
            setPosition(0);
        }
    }

    @Override
    public void onUnfocused() {
        outlined = true;
    }

    @Override
    public void refresh(Screen screen) {
        final BitmapCanvas canvas = screen.canvas();

        int y = (int) titleRenderer.getTextSize();
        titleRenderer.setText(getTitle());
        titleRenderer.setStartPoint(0, y);
        titleRenderer.render(canvas);

        lineRenderer.setStartPoint(0, y);
        lineRenderer.render(canvas);

        y += lineRenderer.getRenderedHeight();
        listRenderer.setStartPoint(0, y);
        listRenderer.render(canvas);

        try {
            screen.drawBitmap(canvas.getBitmap(), 0, 0);
        } catch (IOException e) {
            Log.e(TAG, "Error drawing bitmap to screen!", e);
        }
        canvas.recycle();
    }
}
