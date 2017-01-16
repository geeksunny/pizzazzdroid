package com.radicalninja.pizzazz.util;

public class Margin {

    public int left, top, right, bottom;

    public Margin() {
        // default zero values
    }

    public Margin(final int left, final int top, final int right, final int bottom) {
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.top = top;
    }

}
