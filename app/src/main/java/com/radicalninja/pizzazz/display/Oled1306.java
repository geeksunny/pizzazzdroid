package com.radicalninja.pizzazz.display;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.google.android.things.contrib.driver.ssd1306.BitmapHelper;
import com.google.android.things.contrib.driver.ssd1306.Ssd1306;

import java.io.IOException;

public class Oled1306 extends Screen {

    public final static int DEFAULT_I2C_PORT = 1;

    final Ssd1306 display;

    public Oled1306(final int i2cAddress) throws IOException {
        this(DEFAULT_I2C_PORT, i2cAddress);
    }

    public Oled1306(final int i2cPort, final int i2cAddress) throws IOException {
        @SuppressLint("DefaultLocale") final String portName = String.format("I2C%d", i2cPort);
        display = new Ssd1306(portName, i2cAddress);
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public void clearScreen() throws IOException {
        display.clearPixels();
        display.show();
    }

    @Override
    public void drawBitmap(final Bitmap bitmap, final int x, final int y) throws IOException {
        BitmapHelper.setBmpData(display, x, y, bitmap, false);
        display.show();
    }

}
