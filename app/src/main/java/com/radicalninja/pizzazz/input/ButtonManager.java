package com.radicalninja.pizzazz.input;

import android.support.annotation.NonNull;
import android.util.Log;

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

    private final Map<Pin, ButtonInputDriver> buttonMap = new HashMap<>();

    private AbstractButtonController buttonController;

    public AbstractButtonController getButtonController() {
        return buttonController;
    }

    public void setButtonController(AbstractButtonController buttonController) {
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

}
