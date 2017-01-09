package com.radicalninja.pizzazz.input;

import android.util.Log;
import android.view.KeyEvent;

import com.radicalninja.pizzazz.Pin;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractButtonController {

    final static String TAG = AbstractButtonController.class.getSimpleName();

    final Map<Pin, ButtonCallback> buttonMap = new HashMap<>();

    private void handleButtonEvent(final Pin pin, final int action) {
        if (!buttonMap.containsKey(pin) || null != buttonMap.get(pin)) {
            final ButtonCallback callback = buttonMap.get(pin);
            switch (action) {
                case KeyEvent.ACTION_DOWN:
                    callback.onPressed();
                    break;
                case KeyEvent.ACTION_UP:
                    callback.onReleased();
                    break;
                case KeyEvent.ACTION_MULTIPLE:
                    callback.onHeld();
                    break;
                default:
                    Log.e(TAG, String.format("Key action (%d) not recognized.", action));
            }
        }
    }

    abstract void setupButtons();

    private void setupButton(final Pin pin, final ButtonCallback callback) {
        if (buttonMap.containsKey(pin) && null != buttonMap.get(pin)) {
            Log.e(TAG, String.format("Button on pin %s is already registered to this controller.", pin.pin()));
        } else {
            ButtonManager.INSTANCE.setupButton(pin);
            buttonMap.put(pin, callback);
        }
    }

}
