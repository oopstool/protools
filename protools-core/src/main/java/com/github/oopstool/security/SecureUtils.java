package com.github.oopstool.security;

import com.github.oopstool.string.StringUtils;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;


/**
 * 安全加密工具类
 * <p>
 * 1、摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等<br>
 * 2、对称加密（symmetric），例如：AES、DES等<br>
 * 3、非对称加密（asymmetric），例如：RSA、DSA等<br>
 *
 * @author : HouGY
 * @since : 1.0.3
 */
public class SecureUtils {

    public final static String ALGORITHM_SHA = "SHA";
    public final static String ALGORITHM__SHA256 = "SHA-256";
    public final static String ALGORITHM_MD5 = "MD5";

    /**
     * <p>
     * 根据指定的算法将字符串加密
     * </p>
     *
     * @param source    需要加密的字符串
     * @param algorithm 算法
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果algorithm不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptString(String source, String algorithm)
        throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(StringUtils.bytes(source));
        // 加密
        byte[] digest = messageDigest.digest();
        // 16制编码
        String hexStr = HexUtil.encodeHexStr(digest);
        return hexStr;
    }

    /**
     * <p>
     * 根据指定的算法将字符串加密，添加盐值
     * </p>
     *
     * @param source    需要加密的字符串
     * @param algorithm 算法
     * @param salt 盐值 默认加在头
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果algorithm不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptStringWithSalt(String source, String algorithm,String salt)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(StringUtils.bytes(source));
        // 加密
        byte[] digest = messageDigest.digest(StringUtils.bytes(salt));
        // 16制编码
        String hexStr = HexUtil.encodeHexStr(digest);
        return hexStr;
    }

    /**
     * <p>
     * 根据MD5将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptByMD5(String source) throws NoSuchAlgorithmException {
        return encryptString(source, ALGORITHM_MD5);
    }

    /**
     * <p>
     * 根据MD5将字符串加密，添加盐值
     * </p>
     *
     * @param source 需要加密的字符串
     * @param salt 盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptByMD5(String source,String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM_MD5,salt);
    }


    /**
     * <p>
     * 根据SHA将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA(String source) throws NoSuchAlgorithmException {
        return encryptString(source, ALGORITHM_SHA);
    }

    /**
     * <p>
     * 根据SHA将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @param salt 盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA(String source,String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM_SHA,salt);
    }

    /**
     * <p>
     * 根据SHA256将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @param salt 盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA256(String source,String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM__SHA256,salt);
    }

    /**
     * <p>
     * 根据SHA256将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA256(String source) throws NoSuchAlgorithmException {
        return encryptString(source, ALGORITHM__SHA256);
    }

    public static void readP12Cert() {
        {
            final String KEYSTORE_FILE = "/Users/houguangyu/Desktop/text.p12";
            final String KEYSTORE_PASSWORD = "0923";

            try {
                KeyStore ks = KeyStore.getInstance("PKCS12");
                FileInputStream fis = new FileInputStream(KEYSTORE_FILE);

                // If the keystore password is empty(""), then we have to set
                // to null, otherwise it won't work!!!
                char[] nPassword = null;
                if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
                    nPassword = null;
                } else {
                    nPassword = KEYSTORE_PASSWORD.toCharArray();
                }
                ks.load(fis, nPassword);
                fis.close();

                System.out.println("keystore type=" + ks.getType());

                // Now we loop all the aliases, we need the alias to get keys.
                // It seems that this value is the "Friendly name" field in the
                // detals tab <-- Certificate window <-- view <-- Certificate
                // Button <-- Content tab <-- Internet Options <-- Tools menu
                // In MS IE 6.
                Enumeration<String> aliases = ks.aliases();
                String keyAlias = null;
                // we are readin just one certificate.
                if (aliases.hasMoreElements()) {
                    keyAlias = (String) aliases.nextElement();
                    System.out.println("alias=[" + keyAlias + "]");
                }

                // Now once we know the lias, we could get the keys.
                System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
                PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
                Certificate cert = ks.getCertificate(keyAlias);
                PublicKey pubkey = cert.getPublicKey();

                System.out.println("cert class = " + cert.getClass().getName());
                System.out.println("cert = " + cert);
                System.out.println("public key = " + pubkey.toString());
                System.out.println("private key = " + prikey.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        readP12Cert();
    }
}