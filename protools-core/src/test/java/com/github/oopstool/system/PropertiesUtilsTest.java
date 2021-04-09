package com.github.oopstool.system;

import java.util.Properties;
import org.junit.Test;

public class PropertiesUtilsTest {

    @Test
    public void loadProperties() {
        Properties properties = PropertiesUtils.loadProperties("test.properties");
        String user = properties.getProperty("user");
        System.out.println(user);

    }
}