package com.radicalninja.pizzazz.input;

import android.support.annotation.Nullable;

import com.radicalninja.pizzazz.Pin;

public class DPadButtonController extends AbstractButtonController {

    private final ButtonCallback upCallback;
    private final ButtonCallback downCallback;
    private final ButtonCallback leftCallback;
    private final ButtonCallback rightCallback;

    public DPadButtonController(@Nullable final ButtonCallback upCallback, @Nullable final ButtonCallback downCallback,
                                @Nullable final ButtonCallback leftCallback, @Nullable final ButtonCallback rightCallback) {
        this.upCallback = upCallback;
        this.downCallback = downCallback;
        this.leftCallback = leftCallback;
        this.rightCallback = rightCallback;
    }

    @Override
    void setupButtons() {
        setupButton(Pin.UP, upCallback);
        setupButton(Pin.DOWN, downCallback);
        setupButton(Pin.LEFT, leftCallback);
        setupButton(Pin.RIGHT, rightCallback);
    }
}
