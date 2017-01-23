package com.radicalninja.pizzazz.input;

public interface ButtonCallback {

    void onPressed();

    void onReleased();

    void onHeld();

    class Impl implements ButtonCallback {
        @Override public void onHeld() { }
        @Override public void onPressed() { }
        @Override public void onReleased() { }
    }

}
