package com.radicalninja.pizzazz.led;

import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;
import com.radicalninja.pizzazz.Pin;

import java.io.IOException;

public class LedController {

    private static final String TAG = "LEDController";

    private final PeripheralManagerService pioService = new PeripheralManagerService();

    private Pin pin;
    private Gpio led;

    public LedController(final Pin pin) {
        setupOnPin(pin);
    }

    public void setupOnPin(final Pin pin) {
        setupOnPin(pin, false);
    }

    private void setupOnPin(final Pin pin, final boolean reassign) {
        if (null != led) {
            if (reassign) {
                close();
            } else {
                Log.d(TAG, "LED object is already active.");
                return;
            }
        }
        createLed(pin);
    }

    public void close() {
        try {
            led.close();
        } catch (IOException e) {
            Log.e(TAG, "Error closing LED GPIO", e);
        } finally {
            led = null;
        }
    }

    private void createLed(final Pin pin) {
        try {
            led = pioService.openGpio(pin.pin());
            led.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        } catch (IOException e) {
            Log.e(TAG, String.format("Error configuring GPIO pin %s", pin.pin()), e);
        }
    }

    public boolean isOn() {
        try {
            return led.getValue();
        } catch (IOException e) {
            Log.e(TAG, String.format("Error accessing GPIO value on pin %s", pin.pin()), e);
            return false;
        }
    }

    private void set(final boolean on) {
        try {
            led.setValue(on);
        } catch (IOException e) {
            Log.e(TAG, String.format("Error setting GPIO value on pin %s", pin.pin()), e);
        }
    }

    public void turnOn() {
        set(true);
    }

    public void turnOff() {
        set(false);
    }

    public void toggle() {
        set(!isOn());
    }

}
