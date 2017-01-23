package com.radicalninja.pizzazz.ui;

import com.radicalninja.pizzazz.display.Screen;

public abstract class Window {

    private String title;
    //font wrapper? title + window

    public Window(String title) {
        this.title = title;
    }

    public void open(final Screen screen) {
        setWidth(screen.getWidth());
    }

    public abstract void setWidth(final int width);

    public abstract void refresh(final Screen screen);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO: Abstract method for hooking up windows' ButtonControllers. Callback objects will be members of subclass
}
