package com.radicalninja.pizzazz;


import com.google.android.things.contrib.driver.ssd1306.Ssd1306;
import com.radicalninja.pizzazz.display.Oled1306Screen;
import com.radicalninja.pizzazz.ui.MenuWindow;
import com.radicalninja.pizzazz.ui.WindowManager;

import java.io.IOException;

public class Pizzazz {

    final WindowManager wm;

    public Pizzazz() throws IOException {
        wm = new WindowManager();
        init();
    }

    private void init() throws IOException {
        final Oled1306Screen leftScreen = new Oled1306Screen(Ssd1306.I2C_ADDRESS_ALT);
        final Oled1306Screen rightScreen = new Oled1306Screen(Ssd1306.I2C_ADDRESS);

        final MenuWindow menuLeft = new MenuWindow("Left Window");
        menuLeft.addMenuItem("Abc defg", null);
        menuLeft.addMenuItem("Hijk lmn", null);
        menuLeft.addMenuItem("Opq rstu", null);
        menuLeft.addMenuItem("Vw xyz", null);
        wm.registerDisplay(leftScreen, menuLeft);

        final MenuWindow menuRight = new MenuWindow("Right Window");
        menuRight.addMenuItem("Lorem Ipsum", null);
        menuRight.addMenuItem("Qwerty", null);
        menuRight.addMenuItem("asdf", null);
        menuRight.addMenuItem("jkl;", null);
        menuRight.addMenuItem("Dvorak", null);
        wm.registerDisplay(rightScreen, menuRight);

        wm.start();
    }
}
