package com.radicalninja.pizzazz;

import android.annotation.SuppressLint;
import android.view.KeyEvent;

public enum Pin {

    UP(27, KeyEvent.KEYCODE_BUTTON_1),
    DOWN(5, KeyEvent.KEYCODE_BUTTON_2),
    LEFT(17, KeyEvent.KEYCODE_BUTTON_3),
    RIGHT(22, KeyEvent.KEYCODE_BUTTON_4),
    BACK(6, KeyEvent.KEYCODE_BUTTON_5),
    SELECT(12, KeyEvent.KEYCODE_BUTTON_6),
    REBOOT(18, KeyEvent.KEYCODE_BUTTON_7),
    YELLOW(13, KeyEvent.KEYCODE_BUTTON_8),
    GREEN(19, KeyEvent.KEYCODE_BUTTON_9);

    private final int pin;
    private final int keycode;

    Pin(final int bcmPinNumber, final int keycode) {
        pin = bcmPinNumber;
        this.keycode = keycode;
    }

    @SuppressLint("DefaultLocale")
    public String pin() {
        return String.format("BCM%d", pin);
    }

    public int keycode() {
        return keycode;
    }

}
