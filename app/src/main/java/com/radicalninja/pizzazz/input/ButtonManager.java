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

    private final Map<Pin, Button> pinMap = new HashMap<>();
    private final Map<Button, Pin> buttonMap = new HashMap<>();

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
        if (!pinMap.containsKey(pin) || null == pinMap.get(pin)) {
            try {
                final Button button = new Button(pin.pin(), Button.LogicState.PRESSED_WHEN_LOW);
                button.setOnButtonEventListener(buttonEventListener);
                pinMap.put(pin, button);
                buttonMap.put(button, pin);
            } catch (IOException e) {
                Log.e(TAG, String.format("Error configuring GPIO button on pin %s", pin.pin()), e);
            }
        }
    }

    public void closeButton(@NonNull final Pin pin) {
        if (!pinMap.containsKey(pin)) {
            Log.i(TAG, String.format(
                    "Attempted to close GPIO button on pin %s but it was not registered.", pin.pin()));
            return;
        }
        final Button button = pinMap.get(pin);
        try {
            button.close();
        } catch (IOException e) {
            Log.e(TAG, String.format("Error attempting to close GPIO button on pin %s", pin.pin()), e);
        } finally {
            buttonMap.remove(button);
            pinMap.remove(pin);
        }
    }

    public void cleanup() {
        for (final Pin pin : pinMap.keySet()) {
            closeButton(pin);
        }
        buttonMap.clear();
    }

    private Button.OnButtonEventListener buttonEventListener = new Button.OnButtonEventListener() {
        @Override
        public void onButtonEvent(Button button, boolean pressed) {
            final Pin pin = buttonMap.get(button);
            // TODO: Timer logic for ACTION_MULTIPLE signal
            if (null != pin && null != buttonController) {
                final int action = pressed ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP;
                buttonController.handleButtonEvent(pin, action);
            }
        }
    };

}
