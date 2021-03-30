package com.github.oopstool.file;

import org.junit.Test;

import java.util.Properties;

public class PropertiesUtilsTest {

    @Test
    public void loadProperties() {
        Properties properties = PropertiesUtils.loadProperties("test.properties");
        String user = properties.getProperty("user");
        System.out.println(user);

    }
}