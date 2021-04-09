package com.github.oopstool.security;

import com.github.oopstool.string.StringUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class HexUtilsTest {


    @Test
    public void encodeHex() {
        char[] encodeHex = HexUtils.encodeHex(StringUtils.bytes("cde"));
        System.out.println(encodeHex);
        char[] encodeHex1 = HexUtils.encodeHex(StringUtils.bytes("测试"), true);
        System.out.println(encodeHex1);
        char[] encodeHex2 = HexUtils.encodeHex("cde", StandardCharsets.UTF_8);
        System.out.println(encodeHex2);
    }

    @Test
    public void encodeHexStr() {
        String cde = HexUtils.encodeHexStr(StringUtils.bytes("测试"));
        System.out.println(cde);

        String cde1 = HexUtils.encodeHexStr(StringUtils.bytes("测试"), false);
        System.out.println(cde1);

        String cde2 = HexUtils.encodeHexStr("测试");
        System.out.println(cde2);

        String cde3 = HexUtils.encodeHexStr("测试", StandardCharsets.UTF_8);
        System.out.println(cde3);

        String cde4 = HexUtils.encodeHexStr("测试", StandardCharsets.UTF_16);
        System.out.println(cde4);

        String cde5 = HexUtils.encodeHexStr("测试", Charset.forName("GBK"));
        System.out.println(cde5);

        String decodeHexStr = HexUtils.decodeHexStr(cde);
        System.out.println(decodeHexStr);

        String decodeHexStr1 = HexUtils.decodeHexStr(cde1);
        System.out.println(decodeHexStr1);

        String decodeHexStr2 = HexUtils.decodeHexStr(cde2);
        System.out.println(decodeHexStr2);

        String decodeHexStr3 = HexUtils.decodeHexStr(cde3);
        System.out.println(decodeHexStr3);

        String decodeHexStr4 = HexUtils.decodeHexStr(cde4, StandardCharsets.UTF_16);
        System.out.println(decodeHexStr4);

        String decodeHexStr5 = HexUtils.decodeHexStr(cde5, Charset.forName("GBK"));
        System.out.println(decodeHexStr5);
    }


    @Test
    public void decodeHexStr() {
        String e6B58BE8AF95 = HexUtils.decodeHexStr("E6B58BE8AF95");
        System.out.println(e6B58BE8AF95);
        assert e6B58BE8AF95.equals("测试");
        String e6B58BE8AF95_1 = HexUtils.decodeHexStr("e6b58be8af95");
        System.out.println(e6B58BE8AF95_1);
        assert e6B58BE8AF95_1.equals("测试");

        String e6b58be8af95 = HexUtils.decodeHexStr("e6b58be8af95".toCharArray(), StandardCharsets.UTF_8);
        System.out.println(e6b58be8af95);
    }

    @Test
    public void decodeHex() {
        byte[] e6b58be8af95s = HexUtils.decodeHex("e6b58be8af95");
        System.out.println(e6b58be8af95s);
        byte[] e6b58be8af95s_1 = HexUtils.decodeHex("");
        System.out.println(e6b58be8af95s_1);

        try {
            byte[] e6b58be8af95s_2 = HexUtils.decodeHex("e6b58 be8 af95");
            System.out.println(e6b58be8af95s_2);

            byte[] e6b58be8af95s_3 = HexUtils.decodeHex("2352dsfa4t");
            System.out.println(e6b58be8af95s_3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] bytes = HexUtils.decodeHex("ce".toCharArray());
        System.out.println(bytes.toString());
    }


}