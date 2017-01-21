package com.radicalninja.pizzazz.input;

import android.support.annotation.Nullable;

import com.radicalninja.pizzazz.Pin;

public class OkCancelButtonController extends AbstractButtonController {

    private final ButtonCallback okCallback;
    private final ButtonCallback cancelCallback;

    public OkCancelButtonController(@Nullable final ButtonCallback okCallback, @Nullable final ButtonCallback cancelCallback) {
        this.okCallback = okCallback;
        this.cancelCallback = cancelCallback;
    }

    @Override
    void setupButtons() {
        setupButton(Pin.SELECT, okCallback);
        setupButton(Pin.BACK, cancelCallback);
    }
}
