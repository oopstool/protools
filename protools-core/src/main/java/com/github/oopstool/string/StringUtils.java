package com.github.oopstool.string;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 基于guava的字符串处理工具
 *
 * @author : HouGY
 * @since : 1.0.0
 */
public class StringUtils {

    /**
     * 字符串常量：{@code "null"} <br>
     * 注意：{@code "null" != null}
     */
    public static final String NULL = "null";

    /**
     * 字符串常量：空字符串 {@code ""}
     */
    public static final String EMPTY = "";

    /**
     * 字符串常量：空格符 {@code " "}
     */
    public static final String SPACE = " ";


    /**
     * 判断字符串是否为空，空的定义如下：
     * <p>
     * <li>1：null</li>
     * <li>2：空字符串：""</li>
     * <p>
     * 例：
     * </p>
     * <ul>
     *     {@code StringUtils.isBlank(null)     // true}
     *     {@code StringUtils.isBlank("")       // true}
     *     {@code StringUtils.isBlank("a")    // false}
     * </ul>
     * </p>
     * <p>
     *     注意：该方法与 {@link #isEmpty(String)} 的区别是：
     *      该方法会校验空白字符
     * </p>
     *
     * @param string 目标字符串
     * @return 返回boolean类型判断结果
     */
    public static boolean isBlank(String string) {
        return Strings.isNullOrEmpty(string);
    }

    /**
     * <p>判断字符串是否为空，空的定义如下：</p><br>
     * <ol>
     *     <li>{@code null}</li>
     * </ol>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.isEmpty(null)     // true}</li>
     *     <li>{@code StringUtils.isEmpty("")       // false}</li>
     *     <li>{@code StringUtils.isEmpty("a")    // false}</li>
     * </ul>
     *
     * <p>注意：该方法与 {@link #isBlank(String)} 的区别是：该方法不校验空白字符。</p>
     *
     * @param string 目标字符串
     * @return 返回boolean类型判断结果
     */
    public static boolean isEmpty(String string) {
        return string == null;
    }

    /**
     * <p>将null装换成空串"",如果不为null则返回自己本身：</p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.nullToEmpty(null) return ""}</li>
     *     <li>{@code StringUtils.nullToEmpty("abc") return "abc"}</li>
     * </ul>
     *
     * @param string 目标字符串
     * @return 返回字符串
     */
    public static String nullToEmpty(String string) {
        return Strings.nullToEmpty(string);
    }

    /**
     * <p>将空字符串""装换成null,如果不为""则返回自己本身：</p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.emptyToNull("") return null}</li>
     *     <li>{@code StringUtils.emptyToNull("abc") return "abc"}</li>
     * </ul>
     *
     * @param string 目标字符串
     * @return 返回字符串
     */
    public static String emptyToNull(String string) {
        return Strings.emptyToNull(string);
    }

    /**
     * <p>如果string的长度小于minLength，在string前添加padChar，直到字符串长度为minLength。</p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.padStart("7", 3, '0') returns "007"}</li>
     *     <li>{@code StringUtils.padStart("2010", 3, '0') returns "2010"}</li>
     * </ul>
     *
     * @param string    目标字符串
     * @param minLength 最短长度
     * @param padChar   填充的字符串
     * @return 返回新的字符串
     */
    public static String padStart(String string, int minLength, char padChar) {
        return Strings.padStart(string, minLength, padChar);
    }

    /**
     * <p>如果string的长度小于minLength，在string后添加padChar，直到字符串长度为minLength。</p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.padEnd("4.", 5, '0') returns "4.000"}</li>
     *     <li>{@code StringUtils.padEnd("2010", 3, '!') returns "2010"}</li>
     * </ul>
     * <p>该方法与 {@link #padStart(String, int, char)} 方法用法相同不过是往字符串后边追加。</p>
     *
     * @param string    目标字符串
     * @param minLength 最短长度
     * @param padChar   填充的字符串
     * @return 返回新的字符串
     */
    public static String padEnd(String string, int minLength, char padChar) {
        return Strings.padEnd(string, minLength, padChar);
    }

    /**
     * <p>
     * 拼接字符串。
     * </p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.append(",", "张三", "23") returns "张三,23"}</li>
     * </ul>
     *
     * @param joiner 拼接连接符
     * @param args   要拼接的字符串
     * @return 返回新的字符串
     */
    public static String append(String joiner, Object... args) {
        return Joiner.on(joiner).skipNulls().join(args);
    }
    /**
     * <p>
     * 格式化字符串。
     * </p><br>
     *
     * <p>例：</p>
     * <ul>
     *     <li>{@code StringUtils.format("大家好，我是%s,今年%s。", "张三", 23); returns "大家好，我是张三,今年23。"}</li>
     * </ul>
     *
     * @param template 字符串模版
     * @param args   模版参数
     * @return 返回新的字符串
     */
    public static String format(String template, Object... args) {
        return Strings.lenientFormat(template, args);
    }

    /**
     * 编码字符串， 默认编码为UTF-8
     *
     * @param str 字符串
     * @return 编码后的字节码
     */
    public static byte[] utf8Bytes(CharSequence str) {
        return bytes(str, StandardCharsets.UTF_8);
    }

    /**
     * 编码字符串<br>
     * 使用系统默认编码
     *
     * @param str 字符串
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.defaultCharset());
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, String charset) {
        return bytes(str, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }
        return str.toString().getBytes(charset);
    }



    /**
     * 将byte数组转为字符串
     *
     * @param bytes   byte数组
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * 将Byte数组转为字符串
     *
     * @param bytes   byte数组
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(Byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(Byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = 0; i < data.length; i++) {
            dataByte = data[i];
            bytes[i] = (null == dataByte) ? -1 : dataByte;
        }

        return str(bytes, charset);
    }


    /**
     * 调用对象的toString方法，null会返回“null”
     *
     * @param obj 对象
     * @return 字符串
     * @since 4.1.3
     */
    public static String toString(Object obj) {
        return null == obj ? NULL : obj.toString();
    }


    /**
     * 如果字符串是 {@code null}，则返回指定默认字符串，否则返回字符串本身。
     *<p>
     *   <ul>
     *       {@code nullToDefault(null, &quot;default&quot;)  = &quot;default&quot;}
     *       {@code nullToDefault(&quot;&quot;, &quot;default&quot;)    = &quot;&quot;}
     *       {@code nullToDefault(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;}
     *       {@code nullToDefault(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;}
     *   </ul>
     *</p>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String nullToDefault(CharSequence str, String defaultStr) {
        return (str == null) ? defaultStr : str.toString();
    }



}
