package com.radicalninja.pizzazz.ui;

import android.util.Log;

import com.radicalninja.pizzazz.display.Screen;
import com.radicalninja.pizzazz.input.ButtonController;
import com.radicalninja.pizzazz.input.ButtonCallback;
import com.radicalninja.pizzazz.input.DPadButtonController;
import com.radicalninja.pizzazz.input.OkCancelButtonController;
import com.radicalninja.pizzazz.render.LineRenderer;
import com.radicalninja.pizzazz.render.ListRenderer;
import com.radicalninja.pizzazz.render.TextRenderer;
import com.radicalninja.pizzazz.util.BitmapCanvas;
import com.radicalninja.pizzazz.util.Focusable;
import com.radicalninja.pizzazz.util.Fonts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends Window implements Focusable {

    private final static String TAG = MenuWindow.class.getSimpleName();

    // TODO: Move titleRenderer, title rendering, line rendering code to Window
    private final LineRenderer lineRenderer = new LineRenderer();
    private final TextRenderer titleRenderer;
    private final ListRenderer listRenderer;
    private final ButtonController dPadController, okCancelController;

    private boolean outlined = false;
    private List<MenuItem> items = new ArrayList<>();

    public MenuWindow(String title) {
        super(title);

        dPadController = new DPadButtonController(upCallback, downCallback, null, null);
        okCancelController = new OkCancelButtonController(okCallback, cancelCallback);

        titleRenderer = new TextRenderer(Fonts.CHRONO_TYPE.typeface(), 12);
        listRenderer = new ListRenderer(Fonts.SUPER_MARIO_WORLD.typeface(), 7);

        titleRenderer.setMargin(0);
        listRenderer.setMargin(1);
        setPosition(1);
    }

    private final ButtonCallback upCallback = new ButtonCallback.Impl() {
        @Override
        public void onPressed() {
            setPosition(getPosition() + 1);
        }
    };

    private final ButtonCallback downCallback = new ButtonCallback.Impl() {
        @Override
        public void onPressed() {
            setPosition(getPosition() - 1);
        }
    };

    private final ButtonCallback okCallback = new ButtonCallback.Impl() {
        @Override
        public void onPressed() {
            // TODO execute callback on selected menu item
        }
    };

    private final ButtonCallback cancelCallback = new ButtonCallback.Impl() {
        @Override
        public void onPressed() {
            // TODO go back one item in history stack. onClose?
        }
    };

    @Override
    public ButtonController[] getButtonControllers() {
        return new ButtonController[]{dPadController, okCancelController};
    }

    public int getPosition() {
        return listRenderer.getPosition();
    }

    public void setPosition(int position) {
        listRenderer.setPosition(position);
    }

    public void addMenuItem(final String title, final MenuItem.Callback callback) {
        addMenuItem(new MenuItem(title, callback));
    }

    public void addMenuItem(final MenuItem item) {
        items.add(item);
        listRenderer.addString(item.getTitle());
    }

    @Override
    public void setWidth(int width) {
        titleRenderer.setWidth(width);
        listRenderer.setWidth(width);
        lineRenderer.setWidth(width);
    }

    @Override
    public void onFocused() {
        outlined = false;
        if (getPosition() < 0) {
            setPosition(0);
        }
    }

    @Override
    public void onUnfocused() {
        outlined = true;
    }

    @Override
    public void refresh(Screen screen) {
        final BitmapCanvas canvas = screen.canvas();

        int y = (int) titleRenderer.getTextSize();
        titleRenderer.setText(getTitle());
        titleRenderer.setStartPoint(0, y);
        titleRenderer.render(canvas);

        lineRenderer.setStartPoint(0, y);
        lineRenderer.render(canvas);

        y += lineRenderer.getRenderedHeight();
        listRenderer.setStartPoint(0, y);
        listRenderer.render(canvas);

        try {
            screen.drawBitmap(canvas.getBitmap(), 0, 0);
        } catch (IOException e) {
            Log.e(TAG, "Error drawing bitmap to screen!", e);
        }
        canvas.recycle();
    }
}
