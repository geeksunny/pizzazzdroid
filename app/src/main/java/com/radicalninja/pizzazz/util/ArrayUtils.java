package com.radicalninja.pizzazz.util;

import java.util.Arrays;

public class ArrayUtils {

    public static <T> void reverseArray(final T[] array) {
        final T[] result = Arrays.copyOf(array, array.length);
        int i = 0;
        for (int r = result.length - 1; r >= 0; r--) {
            array[i] = result[r];
            i++;
        }
    }

}
