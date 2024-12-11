package com.raketech.demo.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
    private static final String ymlFile = System.getProperty("user.dir") + "/src/test/resources/config.yml";
    private static Map<String, Object> config;

    static {
        loadConfig();
    }

    /**
     * Loads the YAML configuration file and initializes the settings.
     * Throws detailed exceptions if the file is missing or the configuration is empty.
     */
    private static void loadConfig() {
        try (InputStream input = new FileInputStream(ymlFile)) {
            Yaml yaml = new Yaml();
            config = yaml.load(input);

            if (config == null) {
                throw new RuntimeException("Error loading YAML file: Configuration is empty. Please verify the file content.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading configuration file: " + e.getMessage() +
                ". Ensure the file exists and the path is correct.", e);
        }
    }

    /**
     * Retrieves a value from the YAML configuration using a specified key or path.
     * For nested keys, use dot-separated paths like "parent.child.key".
     *
     * @param key The key or path to the desired value in the YAML configuration.
     * @return The value associated with the provided key, as an Object.
     * @throws IllegalArgumentException if the key is null or empty.
     * @throws IllegalStateException if the key does not exist or the path is invalid.
     */
    public Object getValue(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }

        String[] path = key.split("\\.");
        Map<String, Object> current = config;

        for (int i = 0; i < path.length; i++) {
            String segment = path[i];

            if (i == path.length - 1) {
                if (!current.containsKey(segment)) {
                    throw new IllegalStateException("Key not found in configuration: " + key);
                }
                return current.get(segment);
            }

            Object next = current.get(segment);
            if (!(next instanceof Map)) {
                throw new IllegalStateException("Invalid path in configuration: " + key);
            }
            current = (Map<String, Object>) next;
        }

        throw new IllegalStateException("Key not found in configuration: " + key);
    }
}
