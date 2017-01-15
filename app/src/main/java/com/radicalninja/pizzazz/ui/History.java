package com.radicalninja.pizzazz.ui;

import java.util.LinkedList;

public class History {

    private LinkedList<AbstractWindow> backstack = new LinkedList<>();

    public History() {
        //
    }

    public void add(final AbstractWindow window) {
        backstack.push(window);
    }

    public void popBackstack() {
        backstack.pop();
    }

}
