package com.radicalninja.pizzazz;

import android.annotation.SuppressLint;
import android.view.KeyEvent;

public enum Pin {

    UP(27),
    DOWN(5),
    LEFT(17),
    RIGHT(22),
    BACK(6),
    SELECT(12),
    REBOOT(18),
    YELLOW(13),
    GREEN(19);

    private final int pin;

    Pin(final int bcmPinNumber) {
        pin = bcmPinNumber;
    }

    @SuppressLint("DefaultLocale")
    public String pin() {
        return String.format("BCM%d", pin);
    }

}
