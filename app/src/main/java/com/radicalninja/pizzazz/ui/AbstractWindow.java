package com.radicalninja.pizzazz.ui;

public abstract class AbstractWindow {

    private String title;
    //font wrapper? title + window

    public AbstractWindow(String title) {
        this.title = title;
    }

    public void refresh() {
        // TODO: draw to screen
    }

}
