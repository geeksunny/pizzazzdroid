package com.radicalninja.pizzazz.input;

import android.support.annotation.Nullable;

import com.radicalninja.pizzazz.Pin;

public class ButtonControllers {

    public static ButtonController dPadButtons(@Nullable final ButtonCallback upCallback,
                                               @Nullable final ButtonCallback downCallback,
                                               @Nullable final ButtonCallback leftCallback,
                                               @Nullable final ButtonCallback rightCallback) {
        final ButtonController.Builder builder = new ButtonController.Builder();
        builder.bind(Pin.UP, upCallback);
        builder.bind(Pin.DOWN, downCallback);
        builder.bind(Pin.LEFT, leftCallback);
        builder.bind(Pin.RIGHT, rightCallback);
        return builder.build();
    }

    public static ButtonController okCancelButtons(@Nullable final ButtonCallback okCallback,
                                                   @Nullable final ButtonCallback cancelCallback) {
        final ButtonController.Builder builder = new ButtonController.Builder();
        builder.bind(Pin.SELECT, okCallback);
        builder.bind(Pin.BACK, cancelCallback);
        return builder.build();
    }

}
