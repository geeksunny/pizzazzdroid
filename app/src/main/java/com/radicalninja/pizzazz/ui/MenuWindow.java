package com.radicalninja.pizzazz.ui;

import com.radicalninja.pizzazz.display.AbstractScreen;
import com.radicalninja.pizzazz.util.Focusable;

import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends AbstractWindow implements Focusable {

    private boolean outlined = false;
    private int position = -1;
    private List<MenuItem> items = new ArrayList<>();

    public MenuWindow(String title) {
        super(title);
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
        // TODO: create Canvas, pass to Renderer objects
        // TODO: draw canvas' bitmap to screen
    }
}
