package com.github.oopstool.string;


import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 字符串测试类
 *
 * @author : HouGY
 * @since : 2021/3/16
 */

public class StringUtilsTest {

    @Test
    public void isBlank() {
        String a = "a";
        String b = "";
        String c = null;
        String d = "  ";
        String e = "\r\n";

        Assert.assertFalse(StringUtils.isBlank(a));
        Assert.assertTrue(StringUtils.isBlank(b));
        Assert.assertTrue(StringUtils.isBlank(c));
        Assert.assertTrue(StringUtils.isBlank(d));
        Assert.assertTrue(StringUtils.isBlank(e));
    }

    @Test
    public void isNotBlank() {
        String a = "a";
        String b = "";
        String c = null;
        String d = "  ";
        String e = "\r\n";

        Assert.assertTrue(StringUtils.isNotBlank(a));
        Assert.assertFalse(StringUtils.isNotBlank(b));
        Assert.assertFalse(StringUtils.isNotBlank(c));
        Assert.assertFalse(StringUtils.isNotBlank(d));
        Assert.assertFalse(StringUtils.isNotBlank(e));
    }


    @Test
    public void isEmpty() {
        String a = "a";
        String b = "";
        String c = null;
        Assert.assertFalse(StringUtils.isEmpty(a));
        Assert.assertTrue(StringUtils.isEmpty(b));
        Assert.assertTrue(StringUtils.isEmpty(c));
    }

    @Test
    public void isNotEmpty() {
        String a = "a";
        String b = "";
        String c = null;
        Assert.assertTrue(StringUtils.isNotEmpty(a));
        Assert.assertFalse(StringUtils.isNotEmpty(b));
        Assert.assertFalse(StringUtils.isNotEmpty(c));
    }

    @Test
    public void nullToEmpty() {
        String a = "abac";
        String b = "";
        String c = null;
        String d = "  ";
        String s = StringUtils.nullToEmpty(a);
        Assert.assertEquals("abac",s);
        String s1 = StringUtils.nullToEmpty(b);
        Assert.assertEquals("",s1);
        String s2 = StringUtils.nullToEmpty(c);
        Assert.assertEquals("",s2);
        String s3 = StringUtils.nullToEmpty(d);
        Assert.assertEquals("  ",s3);

    }

    @Test
    public void emptyToNull() {
        String a = "abac";
        String b = "";
        String c = null;
        String s = StringUtils.emptyToNull(a);
        Assert.assertEquals("abac",s);
        String s2 = StringUtils.emptyToNull(b);
        Assert.assertEquals(null,s2);
        String s3 = StringUtils.emptyToNull(c);
        Assert.assertEquals(null,s3);
    }

    @Test
    public void padStart() {
        String a = "aba";
        String b = "abac";
        String s = StringUtils.padStart(a,4,'0');
        Assert.assertEquals("0aba",s);
        String s2 = StringUtils.padStart(b,3,'1');
        Assert.assertEquals("abac",s2);

    }

    @Test
    public void padEnd() {
        String a = "aba";
        String b = "abac";
        String s = StringUtils.padEnd(a,4,'0');
        Assert.assertEquals("aba0",s);
        String s2 = StringUtils.padStart(b,3,'1');
        Assert.assertEquals("abac",s2);
    }


    @Test
    public void append() {
        String append = StringUtils.append("-", "张三", 23);
        System.out.println(append);
    }

    @Test
    public void format() {
        String format = StringUtils.format("大家好，我是%s,今年%s。", "张三", 23);
        System.out.println(format);
    }

    @Test
    public void utf8Bytes() {
        byte[] bytes = StringUtils.utf8Bytes("测试");
        assert bytes.length>1;
    }

    @Test
    public void bytes() {
        byte[] bytes = StringUtils.bytes("测试");
        assert bytes.length>1;

        byte[] bytes1 = StringUtils.bytes("测试", StandardCharsets.UTF_8);
        assert bytes1.length>1;

        byte[] bytes2 = StringUtils.bytes("测试","GBK");
        assert bytes2.length>1;

        byte[] bytes3 = StringUtils.bytes("测试","UTF-8");
        assert bytes3.length>1;
    }

    @Test
    public void str() {
        String str = StringUtils.str("E6B58BE8AF95".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String str1 = StringUtils.str("E6B58BE8AF95".getBytes(StandardCharsets.UTF_8), "UTF-8");
        assert str1.equals("E6B58BE8AF95") && str.equals(str1);

    }

    @Test
    public void testToString() {
        String toString = StringUtils.toString("325");
        System.out.println(toString);
    }


}
