package com.radicalninja.pizzazz.ui;

import com.radicalninja.pizzazz.display.AbstractScreen;

public abstract class AbstractWindow {

    private String title;
    //font wrapper? title + window

    public AbstractWindow(String title) {
        this.title = title;
    }

    public void open(final AbstractScreen screen) {
        setWidth(screen.getWidth());
    }

    public abstract void setWidth(final int width);

    public abstract void refresh(final AbstractScreen screen);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO: Abstract method for hooking up windows' ButtonControllers. Callback objects will be members of subclass
}
