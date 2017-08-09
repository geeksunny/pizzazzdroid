package com.radicalninja.pizzazz.util;

import com.google.android.things.contrib.driver.hts221.Hts221;
import com.google.android.things.contrib.driver.lps25h.Lps25h;
import com.google.android.things.contrib.driver.sensehat.SenseHat;

import java.io.File;
import java.io.IOException;

public class CpuTemp {

    private static final String DEFAULT_TEMP_PATH = "/sys/class/thermal/thermal_zone0/temp";

    private final File source;

    public static float getAmbientTemperature(final float cpuTemp, final float humidtyTemp, final float pressureTemp) {
        return ((pressureTemp + humidtyTemp) / 2) - (cpuTemp / 5);
//        return ((pressureTemp + humidtyTemp + pressureTemp) / 3) - (cpuTemp / 5);
    }

    public static float getAmbientTemperature(final CpuTemp cpuTemp, final Hts221 humidity, final Lps25h pressure) throws IOException {
        final float ct = cpuTemp.getTemperature();
        final float ht = humidity.readTemperature();
        final float pt = pressure.readTemperature();
        return getAmbientTemperature(ct, ht, pt);
    }

    public static float celsiusToFarenheit(final float celsius) {
        return celsius * 9.0f / 5.0f + 32.0f;
    }

    public CpuTemp(final String filePath) {
        source = new File(filePath);
    }

    public CpuTemp() {
        this(DEFAULT_TEMP_PATH);
    }

    public float getTemperature() throws IOException {
        return getTemperatureInCelsius();
    }

    public float getTemperatureInCelsius() throws IOException {
        return read();
    }

    public float getTemperatureInFarenheit() throws IOException {
        final float temperature = read();
        return celsiusToFarenheit(temperature);
    }

    private float read() throws IOException {
        final String contents = FileUtils.readFileAsString(source);
        return Float.valueOf(contents) / 1000;
    }

}
