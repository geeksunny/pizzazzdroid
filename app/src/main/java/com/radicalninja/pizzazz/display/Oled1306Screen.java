package com.radicalninja.pizzazz.display;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.google.android.things.contrib.driver.ssd1306.Ssd1306;

import java.io.IOException;

public class Oled1306Screen extends AbstractScreen {

    public final static int DEFAULT_I2C_PORT = 1;

    final Ssd1306 display;

    public Oled1306Screen(final int i2cAddress) throws IOException {
        this(DEFAULT_I2C_PORT, i2cAddress);
    }

    public Oled1306Screen(final int i2cPort, final int i2cAddress) throws IOException {
        @SuppressLint("DefaultLocale") final String portName = String.format("I2C%d", i2cPort);
        display = new Ssd1306(portName, i2cAddress);
    }

    @Override
    public void clearScreen() {
        display.clearPixels();
    }

    @Override
    public void drawBitmap(final Bitmap bitmap) {
        // TODO
    }
}
