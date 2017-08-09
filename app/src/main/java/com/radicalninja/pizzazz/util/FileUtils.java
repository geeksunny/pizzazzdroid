package com.radicalninja.pizzazz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String readFileAsString(final File file) throws IOException {
        final StringBuffer sb = new StringBuffer();
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final char[] buf = new char[1024];
        int bytesRead;
        while((bytesRead = reader.read(buf)) != -1) {
            final String input = String.valueOf(buf, 0, bytesRead);
            sb.append(input);
        }
        reader.close();
        return sb.toString();
    }

}
