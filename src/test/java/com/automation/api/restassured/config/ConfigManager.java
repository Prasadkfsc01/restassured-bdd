package com.automation.api.restassured.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input =
                ConfigManager.class
                    .getClassLoader()
                    .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "config.properties file was not found");
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties", e);
        }
    }

    private ConfigManager() {
    }

    public static String getBaseUrl() {

        // 1. Command line / Maven override
        String systemProperty = System.getProperty("base.url");

        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        // 2. CI/CD environment variable
        String environmentVariable = System.getenv("BASE_URL");

        if (environmentVariable != null
                && !environmentVariable.isBlank()) {
            return environmentVariable;
        }

        // 3. Default config.properties value
        return PROPERTIES.getProperty("base.url");
    }
}