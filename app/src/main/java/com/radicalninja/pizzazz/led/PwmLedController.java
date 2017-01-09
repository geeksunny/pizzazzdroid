package com.radicalninja.pizzazz.led;

import com.radicalninja.pizzazz.Pin;

public class PwmLedController extends LedController {

    public PwmLedController(Pin pin) {
        super(pin);
    }

    public void blink(final int onTime, final int offTime, final int fadeInTime, final int fadeOutTime,
                      final int n, final boolean background) {
        // default: 1, 1, 0, 0, 0(infinite), true
    }

    public void pulse(final int fadeInTime, final int fadeOutTime, final int n, final boolean background) {
        // default: 1, 1, 0(infinite), true
    }

}
