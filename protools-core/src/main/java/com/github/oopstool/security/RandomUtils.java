package com.github.oopstool.security;

import java.util.concurrent.ThreadLocalRandom;
import com.github.oopstool.string.StringUtils;

/**
 * 基于ThreadLocalRandom随机数生成工具
 *
 * @author : HouGY
 * @since : 2021/3/18
 */
public class RandomUtils {
    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_CHAR = "0123456789";


    /**
     * <p>
     *     根据指定的字符串样本获得一个指定长度的随机字符串
     *     如果{@code baseString}为空null或者""则返回""
     * </p>
     *
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     */
    public static String randomString(String baseString, int length) {
        if (StringUtils.isBlank(baseString)) {
            return StringUtils.EMPTY;
        }
        final StringBuilder sb = new StringBuilder(length);
        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = ThreadLocalRandom.current().nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }

    /**
     * <p>
     *     获得一个随机的字符串（只包含数字和字母）
     *     字符串样本为"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
     * </p>
     *
     * <p>
     *     注意 如果自定义字符串样本请使用{@link #randomString(String, int)}}
    * </p>
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        return randomString(ALL_CHAR, length);
    }

    /**
     * <p>
     *     获得一个只包含数字的字符串
     *     数字样本"0123456789" 如果需要自定义数字样本请使用{@link #randomString(String, int)}
     * </p>
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomNumbers(int length) {
        return randomString(NUMBER_CHAR, length);
    }


    /**
     * <p>
     *     获得随机数int值
     * </p>
     *
     * @return 随机数
     */
    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt();
    }
    /**
     * <p>
     *     获得指定范围内的随机数 [0,limit)
     * </p>
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static int randomInt(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    /**
     * <p>
     *     获得指定范围内的随机数
     * </p>
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * <p>
     *     获得随机数long值
     * </p>
     *
     * @return 随机数
     */
    public static long randomLong() {
        return ThreadLocalRandom.current().nextLong();
    }
    /**
     * <p>
     *     获得指定范围内的随机数 [0,limit)
     * </p>
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static long randomLong(long limit) {
        return ThreadLocalRandom.current().nextLong(limit);
    }

    /**
     * <p>
     *     获得指定范围内的随机数
     * </p>
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static long randomLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    /**
     * <p>
     *     获得随机数double值
     * </p>
     *
     * @return 随机数
     */
    public static double randomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }
    /**
     * <p>
     *     获得指定范围内的随机数 [0,limit)
     * </p>
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static double randomDouble(double limit) {
        return ThreadLocalRandom.current().nextDouble(limit);
    }

    /**
     * <p>
     *     获得指定范围内的随机数
     * </p>
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return 随机数
     */
    public static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
