package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new IllegalStateException("config.properties not found in classpath");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    private Config() {

    }

    public static String get(String key) {
        return System.getProperty(key, System.getenv().getOrDefault(key.toUpperCase().replace('.', '_'), properties.getProperty(key)));
    }

    public static String getOrDefault(String key, String defaultValue) {
        return System.getProperty(key, System.getenv().getOrDefault(key.toUpperCase().replace('.', '_'), properties.getProperty(key, defaultValue)));
    }
}
