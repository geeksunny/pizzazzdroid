package com.radicalninja.pizzazz.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

public enum Fonts {

    SUPER_MARIO_WORLD("Super-Mario-World.ttf"),
    CHRONO_TYPE("ChronoType.ttf");

    private static final String TAG = Fonts.class.getSimpleName();

    private static AssetManager assetManager;

    public static void init(final AssetManager assetManager) {
        if (null == Fonts.assetManager) {
            Fonts.assetManager = assetManager;
        }
    }

    private final String filename;

    private Typeface typeface;

    Fonts(final String filename) {
        this.filename = filename;
    }

    private void prepareTypeface() throws NotInitializedException {
        if (null == assetManager) {
            throw new NotInitializedException("Fonts class has not been initialized.");
        }
        typeface = Typeface.createFromAsset(assetManager, filename);
    }

    public Typeface typeface() {
        if (null == typeface) {
            try {
                prepareTypeface();
            } catch (NotInitializedException e) {
                Log.e(TAG, "Unable to create typeface object.", e);
                typeface = Typeface.DEFAULT;
            }
        }
        return typeface;
    }


}
