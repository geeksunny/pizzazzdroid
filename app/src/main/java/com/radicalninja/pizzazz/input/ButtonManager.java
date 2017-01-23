package com.radicalninja.pizzazz.input;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.button.ButtonInputDriver;
import com.radicalninja.pizzazz.Pin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum ButtonManager {

    // TODO: Button Callback system to tie into main activity onkeyevent()
    INSTANCE;

    private final static String TAG = "ButtonManager";

    private final SparseArray<Pin> keyMap = new SparseArray<>();
    private final Map<Pin, ButtonInputDriver> buttonMap = new HashMap<>();

    private ButtonController buttonController;

    public ButtonController getButtonController() {
        return buttonController;
    }

    public void setButtonController(ButtonController buttonController) {
        this.buttonController = buttonController;
    }

    public void setupButton(@NonNull final Pin pin) {
        // TODO: Work in bouncetime – may require modification to the button driver
        // TODO: Work in holdTime, holdRepeat logic manually or within button driver – use KeyEvent.ACTION_MULTIPLE
        if (!buttonMap.containsKey(pin) || null == buttonMap.get(pin)) {
            try {
                final ButtonInputDriver button =
                        new ButtonInputDriver(pin.pin(), Button.LogicState.PRESSED_WHEN_LOW, pin.keycode());
                button.register();
                buttonMap.put(pin, button);
                keyMap.put(pin.keycode(), pin);
            } catch (IOException e) {
                Log.e(TAG, String.format("Error configuring GPIO button on pin %s", pin.pin()), e);
            }
        }
    }

    public void closeButton(@NonNull final Pin pin) {
        if (!buttonMap.containsKey(pin)) {
            Log.i(TAG, String.format(
                    "Attempted to close GPIO button on pin %s but it was not registered.", pin.pin()));
            return;
        }
        final ButtonInputDriver button = buttonMap.get(pin);
        try {
            button.unregister();
            button.close();
        } catch (IOException e) {
            Log.e(TAG, String.format("Error attempting to close GPIO button on pin %s", pin.pin()), e);
        } finally {
            buttonMap.remove(pin);
        }
    }

    public void cleanup() {
        for (final Pin pin : buttonMap.keySet()) {
            closeButton(pin);
        }
        buttonMap.clear();
    }

    boolean onKeyEvent(final int keyCode, final int keyAction) {
        final Pin pin = INSTANCE.keyMap.get(keyCode);
        if (null == pin || null == buttonController) {
            return false;
        } else {
            buttonController.handleButtonEvent(pin, keyAction);
            return true;
        }
    }

    public static boolean onKeyUp(final int keyCode) {
        return INSTANCE.onKeyEvent(keyCode, KeyEvent.ACTION_UP);
    }

    public static boolean onKeyDown(final int keyCode) {
        return INSTANCE.onKeyEvent(keyCode, KeyEvent.ACTION_DOWN);
    }

    public static boolean onKeyMultiple(final int keyCode) {
        return INSTANCE.onKeyEvent(keyCode, KeyEvent.ACTION_MULTIPLE);
    }

}
