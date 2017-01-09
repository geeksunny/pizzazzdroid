package com.radicalninja.pizzazz.led;

import com.radicalninja.pizzazz.Pin;

public class AlertLedController extends PwmLedController {

    public AlertLedController(Pin pin) {
        super(pin);
    }

    public void bounce() {
        // stark blinking
    }

    public void ding() {
        // solid led on
    }

    public void alert() {
        // slow pulse
    }

}
