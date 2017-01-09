package com.radicalninja.pizzazz.ui;

public class MenuItem {

    public interface Callback {
        void onClick();
    }

    private String title;
    private Callback callback;

    public MenuItem(String title, Callback callback) {
        this.title = title;
        this.callback = callback;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
