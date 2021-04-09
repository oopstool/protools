package com.github.oopstool.system;

import com.github.oopstool.system.PropertiesUtils;
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