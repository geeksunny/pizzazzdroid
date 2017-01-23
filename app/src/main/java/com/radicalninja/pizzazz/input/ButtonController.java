package com.radicalninja.pizzazz.input;

import android.util.Log;
import android.view.KeyEvent;

import com.radicalninja.pizzazz.Pin;

import java.util.HashMap;
import java.util.Map;

public class ButtonController {

    final static String TAG = ButtonController.class.getSimpleName();

    final Map<Pin, ButtonCallback> buttonMap = new HashMap<>();

    public ButtonController() {
    }

    private ButtonController(final Map<Pin, ButtonCallback> pinMap) {
        for (final Map.Entry<Pin, ButtonCallback> entry : pinMap.entrySet()) {
            setupButton(entry.getKey(), entry.getValue());
        }
    }

    void handleButtonEvent(final Pin pin, final int action) {
        Log.d(TAG, "ButtonController.handleButtonEvent!");
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

    protected void setupButton(final Pin pin, final ButtonCallback callback) {
        if (buttonMap.containsKey(pin) && null != buttonMap.get(pin)) {
            Log.e(TAG, String.format("Button on pin %s is already registered to this controller.", pin.pin()));
        } else if (null != callback) {
            ButtonManager.INSTANCE.setupButton(pin);
            buttonMap.put(pin, callback);
        }
    }

    public static class Builder {
        private final Map<Pin, ButtonCallback> pinMap = new HashMap<>();

        public void bind(final Pin pin, final ButtonCallback buttonCallback) {
            pinMap.put(pin, buttonCallback);
        }

        public ButtonController build() {
            return new ButtonController(pinMap);
        }
    }

}
