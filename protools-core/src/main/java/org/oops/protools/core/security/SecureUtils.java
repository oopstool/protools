package org.oops.protools.core.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jetbrains.annotations.NotNull;

/**
 * 安全加密工具类
 * <p>
 * 1、摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等<br>
 * 2、对称加密（symmetric），例如：AES、DES等<br>
 * 3、非对称加密（asymmetric），例如：RSA、DSA等<br>
 *
 * @author : HouGY
 * @since : 2021/3/19
 */
public class SecureUtils {

    public final static String ALGORITHM_SHA = "SHA";
    public final static String ALGORITHM__SHA256 = "SHA-256";
    public final static String ALGORITHM_MD5="MD5";

    /**
     * <p>
     *     根据指定的算法将字符串加密
     * </p>
     *
     * @param source    需要加密的字符串
     * @param algorithm 算法
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果algorithm不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptString(String source, @NotNull String algorithm)
        throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
        // TODO 加盐规则
        messageDigest.update("ahkq1".getBytes(StandardCharsets.UTF_8));
        // 加密
        byte[] digest = messageDigest.digest();
        // 16制编码
        StringBuilder hexValue = new StringBuilder();
        for (byte b : digest) {
            int val = ((int) b) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    /**
     * <p>
     *     根据MD5将字符串加密
     * </p>
     *
     * @param source    需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptByMD5(String source) throws NoSuchAlgorithmException {
       return encryptString(source,ALGORITHM_MD5);
    }

    /**
     * <p>
     *     根据SHA将字符串加密
     * </p>
     *
     * @param source    需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA(String source) throws NoSuchAlgorithmException {
        return encryptString(source,ALGORITHM_SHA);
    }

    /**
     * <p>
     *     根据SHA256将字符串加密
     * </p>
     *
     * @param source    需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA256(String source) throws NoSuchAlgorithmException {
        return encryptString(source,ALGORITHM__SHA256);
    }

}
