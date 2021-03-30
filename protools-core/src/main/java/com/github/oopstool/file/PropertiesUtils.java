package com.github.oopstool.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * 配置文件解析
 *
 * @author : HouGY
 * @since : 1.0.4
 */
public class PropertiesUtils {

    /**
     * 根据配置文件路径加载配置文件。方法忽略IOException异常，如果异常会返回空对象
     *
     * @param propertyFileName 文件地址
     * @return 返回Properties 对象
     */
    public static Properties loadProperties(String propertyFileName) {
        InputStreamReader in;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        in = new InputStreamReader(Objects.requireNonNull(loader.getResourceAsStream(propertyFileName)), StandardCharsets.UTF_8);
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }


}
