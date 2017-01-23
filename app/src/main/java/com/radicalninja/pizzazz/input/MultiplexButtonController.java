package com.radicalninja.pizzazz.input;

import com.radicalninja.pizzazz.Pin;
import com.radicalninja.pizzazz.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class MultiplexButtonController extends ButtonController {

    // TODO: add boolean return values and event consumption to handleButtonEvent

    private List<ButtonController> buttonControllers = new ArrayList<>();

    public void registerController(final ButtonController controller) {
        registerController(controller, false);
    }

    public void registerController(final ButtonController controller, final boolean frontOfQueue) {
        if (buttonControllers.contains(controller)) {
            return;
        }
        if (frontOfQueue) {
            buttonControllers.add(0, controller);
        } else {
            buttonControllers.add(controller);
        }
    }

    public void registerControllers(final ButtonController[] controllers) {
        registerControllers(controllers, false);
    }

    public void registerControllers(final ButtonController[] controllers, final boolean frontOfQueue) {
        if (frontOfQueue) {
            ArrayUtils.reverseArray(controllers);
        }
        for (final ButtonController controller : controllers) {
            registerController(controller, frontOfQueue);
        }
    }

    public void unregisterController(final ButtonController controller) {
        if (null != controller) {
            buttonControllers.remove(controller);
        }
    }

    public void unregisterControllers(final ButtonController[] controllers) {
        if (null != controllers) {
            for (final ButtonController controller : controllers) {
                unregisterController(controller);
            }
        }
    }

    @Override
    void handleButtonEvent(Pin pin, int action) {
        for (final ButtonController controller : buttonControllers) {
            controller.handleButtonEvent(pin, action);
        }
    }
}
