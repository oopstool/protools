package com.github.oopstool.file;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataSizeUtilsTest {

    @Test
    public void parseTest(){
      long parse = DataSizeUtils.parse("3M");
        Assert.assertEquals(3145728, parse);

        parse = DataSizeUtils.parse("3m");
        Assert.assertEquals(3145728, parse);

        parse = DataSizeUtils.parse("3MB");
        Assert.assertEquals(3145728, parse);

        parse = DataSizeUtils.parse("3mb");
        Assert.assertEquals(3145728, parse);

        parse = DataSizeUtils.parse("3.1M");
        Assert.assertEquals(3250585, parse);

        parse = DataSizeUtils.parse("3.1m");
        Assert.assertEquals(3250585, parse);

        parse = DataSizeUtils.parse("3.1MB");
        Assert.assertEquals(3250585, parse);

        parse = DataSizeUtils.parse("-3.1MB");
        Assert.assertEquals(-3250585, parse);

        parse = DataSizeUtils.parse("+3.1MB");
        Assert.assertEquals(3250585, parse);

        parse = DataSizeUtils.parse("3.1mb");
        Assert.assertEquals(3250585, parse);

        parse = DataSizeUtils.parse("3.1");
        Assert.assertEquals(3, parse);

        try {
            DataSizeUtils.parse("3.1.3");
        } catch (IllegalArgumentException ie) {
            Assert.assertEquals("'3.1.3' is not a valid data size", ie.getMessage());
        }


    }

    @Test
    public void formatTest(){
        String format = DataSizeUtils.format(Long.MAX_VALUE);
        Assert.assertEquals("8 EB", format);

        format = DataSizeUtils.format(1024L * 1024 * 1024 * 1024 * 1024);
        Assert.assertEquals("1 PB", format);

        format = DataSizeUtils.format(1024L * 1024 * 1024 * 1024);
        Assert.assertEquals("1 TB", format);
    }
}