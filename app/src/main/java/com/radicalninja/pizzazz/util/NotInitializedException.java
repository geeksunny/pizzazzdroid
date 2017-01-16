package com.radicalninja.pizzazz.util;

import java.io.IOException;

public class NotInitializedException extends IOException {
    public NotInitializedException() {
    }

    public NotInitializedException(Throwable cause) {
        super(cause);
    }

    public NotInitializedException(String message) {
        super(message);
    }

    public NotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }
}
