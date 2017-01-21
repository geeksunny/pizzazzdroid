package com.radicalninja.pizzazz.input;

import com.radicalninja.pizzazz.Pin;

import java.util.ArrayList;
import java.util.List;

public class MultiplexButtonController extends AbstractButtonController {

    // TODO: add boolean return values and event consumption to handleButtonEvent

    private List<AbstractButtonController> buttonControllers = new ArrayList<>();

    public void registerController(final AbstractButtonController controller) {
        registerController(controller, false);
    }

    public void registerController(final AbstractButtonController controller, final boolean frontOfQueue) {
        if (frontOfQueue) {
            buttonControllers.add(0, controller);
        } else {
            buttonControllers.add(controller);
        }
    }

    public void unregisterController(final AbstractButtonController controller) {
        if (null != controller) {
            buttonControllers.remove(controller);
        }
    }

    @Override
    void handleButtonEvent(Pin pin, int action) {
        for (final AbstractButtonController controller : buttonControllers) {
            controller.handleButtonEvent(pin, action);
        }
    }

    @Override
    void setupButtons() {
        //
    }
}
