package com.github.oopstool.security;

import com.github.oopstool.string.StringUtils;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class HexUtilTest {


    @Test
    public void encodeHex() {
        char[] encodeHex = HexUtil.encodeHex(StringUtils.bytes("cde"));
        System.out.println(encodeHex);
        char[] encodeHex1 = HexUtil.encodeHex(StringUtils.bytes("测试"),true);
        System.out.println(encodeHex1);
        char[] encodeHex2 = HexUtil.encodeHex("cde", StandardCharsets.UTF_8);
        System.out.println(encodeHex2);
    }

    @Test
    public void encodeHexStr() {
        String cde = HexUtil.encodeHexStr(StringUtils.bytes("测试"));
        System.out.println(cde);

        String cde1 = HexUtil.encodeHexStr(StringUtils.bytes("测试"),false);
        System.out.println(cde1);


        String cde2 = HexUtil.encodeHexStr("测试");
        System.out.println(cde2);

        String cde3 = HexUtil.encodeHexStr("测试",StandardCharsets.UTF_8);
        System.out.println(cde3);

        String cde4 = HexUtil.encodeHexStr("测试",StandardCharsets.UTF_16);
        System.out.println(cde4);

        String cde5 = HexUtil.encodeHexStr("测试",Charset.forName("GBK"));
        System.out.println(cde5);






        String decodeHexStr = HexUtil.decodeHexStr(cde);
        System.out.println(decodeHexStr);

        String decodeHexStr1 = HexUtil.decodeHexStr(cde1);
        System.out.println(decodeHexStr1);

        String decodeHexStr2 = HexUtil.decodeHexStr(cde2);
        System.out.println(decodeHexStr2);

        String decodeHexStr3 = HexUtil.decodeHexStr(cde3);
        System.out.println(decodeHexStr3);

        String decodeHexStr4 = HexUtil.decodeHexStr(cde4,StandardCharsets.UTF_16);
        System.out.println(decodeHexStr4);

        String decodeHexStr5 = HexUtil.decodeHexStr(cde5,Charset.forName("GBK"));
        System.out.println(decodeHexStr5);
    }


    @Test
    public void decodeHexStr() {
        String e6B58BE8AF95 = HexUtil.decodeHexStr("E6B58BE8AF95");
        System.out.println(e6B58BE8AF95);
        assert e6B58BE8AF95.equals("测试");
        String e6B58BE8AF95_1 = HexUtil.decodeHexStr("e6b58be8af95");
        System.out.println(e6B58BE8AF95_1);
        assert e6B58BE8AF95_1.equals("测试");

        String e6b58be8af95 = HexUtil.decodeHexStr("e6b58be8af95".toCharArray(),StandardCharsets.UTF_8);
        System.out.println(e6b58be8af95);
    }

    @Test
    public void decodeHex() {
        byte[] e6b58be8af95s = HexUtil.decodeHex("e6b58be8af95");
        System.out.println(e6b58be8af95s);
        byte[] e6b58be8af95s_1 = HexUtil.decodeHex("");
        System.out.println(e6b58be8af95s_1);

        try {
            byte[] e6b58be8af95s_2 = HexUtil.decodeHex("e6b58 be8 af95");
            System.out.println(e6b58be8af95s_2);

            byte[] e6b58be8af95s_3 = HexUtil.decodeHex("2352dsfa4t");
            System.out.println(e6b58be8af95s_3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] bytes = HexUtil.decodeHex("ce".toCharArray());
        System.out.println(bytes.toString());
    }


}