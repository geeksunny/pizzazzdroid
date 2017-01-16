package com.radicalninja.pizzazz.ui;

import android.graphics.Canvas;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.render.TextRenderer;
import com.radicalninja.pizzazz.util.Focusable;
import com.radicalninja.pizzazz.util.Fonts;

import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends AbstractWindow implements Focusable {

    private final TextRenderer titleRenderer;
    private final TextRenderer itemRenderer;

    private boolean outlined = false;
    private int position = -1;
    private List<MenuItem> items = new ArrayList<>();

    public MenuWindow(String title) {
        super(title);
        titleRenderer = new TextRenderer(Fonts.CHRONO_TYPE.typeface());
        itemRenderer = new TextRenderer(Fonts.SUPER_MARIO_WORLD.typeface());
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
        final Canvas canvas = screen.canvas();


        // TODO: create Canvas, pass to Renderer objects
        // TODO: draw canvas' bitmap to screen
    }
}
