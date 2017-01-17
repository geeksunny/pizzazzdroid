package com.radicalninja.pizzazz;

import android.app.Application;
import android.util.Log;

import com.google.android.things.contrib.driver.ssd1306.Ssd1306;
import com.radicalninja.pizzazz.display.Oled1306Screen;
import com.radicalninja.pizzazz.ui.AbstractWindow;
import com.radicalninja.pizzazz.ui.MenuWindow;
import com.radicalninja.pizzazz.ui.WindowManager;
import com.radicalninja.pizzazz.util.Fonts;

import java.io.IOException;

public class Pizzazz extends Application {

    private static final String TAG = Pizzazz.class.getSimpleName();

    private static Pizzazz instance;

    public static Pizzazz getInstance() {
        return instance;
    }

    public Pizzazz() {
        instance = this;
        Fonts.init(getAssets());
    }

    public void setupHwRev2(final WindowManager wm) {
        final MenuWindow menuLeft = new MenuWindow("Left Window");
        menuLeft.addMenuItem("Abc defg", null);
        menuLeft.addMenuItem("Hijk lmn", null);
        menuLeft.addMenuItem("Opq rstu", null);
        menuLeft.addMenuItem("Vw xyz", null);
        initDisplay(Ssd1306.I2C_ADDRESS_ALT, menuLeft, wm);

        final MenuWindow menuRight = new MenuWindow("Right Window");
        menuRight.addMenuItem("Lorem Ipsum", null);
        menuRight.addMenuItem("Qwerty", null);
        menuRight.addMenuItem("asdf", null);
        menuRight.addMenuItem("jkl;", null);
        menuRight.addMenuItem("Dvorak", null);
        initDisplay(Ssd1306.I2C_ADDRESS, menuRight, wm);
    }

    private boolean initDisplay(final int i2cAddress, final AbstractWindow firstWindow, final WindowManager wm) {
        try {
            final Oled1306Screen screen = new Oled1306Screen(i2cAddress);
            wm.registerDisplay(screen, firstWindow);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "There was an error attempting to open screen", e);
            return false;
        }
    }

}
