package com.radicalninja.pizzazz.ui;

import java.util.LinkedList;

public class History {

    private LinkedList<Window> backstack = new LinkedList<>();

    public History() {
        //
    }

    public void add(final Window window) {
        backstack.push(window);
    }

    public void popBackstack() {
        backstack.pop();
    }

}
