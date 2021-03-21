package org.oops.protools.core.string;


import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertFalse(StringUtils.isBlank(a));
        Assert.assertTrue(StringUtils.isBlank(b));
        Assert.assertTrue(StringUtils.isBlank(c));
        Assert.assertTrue(StringUtils.isBlank(d));
    }


    @Test
    public void isEmpty() {
        String a = "a";
        String b = "";
        String c = null;
        Assert.assertFalse(StringUtils.isEmpty(a));
        Assert.assertFalse(StringUtils.isEmpty(b));
        Assert.assertTrue(StringUtils.isEmpty(c));
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


}
