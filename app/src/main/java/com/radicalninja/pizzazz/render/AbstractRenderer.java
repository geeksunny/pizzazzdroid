package com.radicalninja.pizzazz.render;

import android.graphics.Canvas;

public abstract class AbstractRenderer {

    private boolean invertColors = false;

    public AbstractRenderer() {
        //
    }

    public boolean isInvertColors() {
        return invertColors;
    }

    public void setInvertColors(boolean invertColors) {
        this.invertColors = invertColors;
    }

    abstract void render(final Canvas canvas);

}
