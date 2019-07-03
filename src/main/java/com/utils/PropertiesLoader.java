package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static com.variables.PropertiesKey.ENVIRONMENT;

public class PropertiesLoader {

    private static final String separator = File.separator;

    /**
     * Loads and Returns properties instance.
     */
    public static Properties loadProperties(String filePath) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return properties;
    }

    /**
     * Loads and Returns UI properties instance.
     */
    public Properties loadUIProperties() throws Exception {
        String filePath =
                System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                        + "resources" + separator + "properties" + separator;
        String environment = loadProperties(filePath + "env.properties").getProperty(ENVIRONMENT);
        return loadProperties(filePath + environment + ".properties");
    }
}
