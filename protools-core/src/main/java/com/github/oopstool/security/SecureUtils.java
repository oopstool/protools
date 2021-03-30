package com.github.oopstool.security;

import com.github.oopstool.string.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;


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
    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_PKCS12 = "PKCS12";
    /**
     * 密钥长度
     */
    private static final int KEY_SIZE = 2048;

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
     * @param salt      盐值 默认加在头
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果algorithm不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptStringWithSalt(String source, String algorithm, String salt)
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
     * @param salt   盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptByMD5(String source, String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM_MD5, salt);
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
     * @param salt   盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA(String source, String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM_SHA, salt);
    }

    /**
     * <p>
     * 根据SHA256将字符串加密
     * </p>
     *
     * @param source 需要加密的字符串
     * @param salt   盐值
     * @return 返回加密的字符串
     * @throws NoSuchAlgorithmException 如果MD5算法不存在抛出NoSuchAlgorithmException异常
     */
    public static String encryptBySHA256(String source, String salt) throws NoSuchAlgorithmException {
        return encryptStringWithSalt(source, ALGORITHM__SHA256, salt);
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


    /**
     * 随机生成密钥对（包含公钥和私钥）
     */
    public static KeyPair generateKeyPair() throws Exception {
        // 获取指定算法的密钥对生成器
        KeyPairGenerator gen = KeyPairGenerator.getInstance(ALGORITHM_RSA);

        // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
        gen.initialize(KEY_SIZE);

        // 随机生成一对密钥（包含公钥和私钥）
        return gen.generateKeyPair();
    }

    /**
     * 将 公钥/私钥 编码后以 Base64 的格式
     *
     * @return 编码后以 Base64 的格式
     */
    public static String keyForEncodedBase64(Key key) {
        // 获取密钥编码后的格式
        byte[] encBytes = key.getEncoded();
        // 转换为 Base64 文本
        return new BASE64Encoder().encode(encBytes);
    }

    /**
     * 根据base64创建公钥对象
     *
     * @param pubKeyBase64 base公钥字符串
     * @return 返回公钥对象
     * @throws NoSuchAlgorithmException, IOException, InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String pubKeyBase64) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        // 把 公钥的Base64文本 转换为已编码的 公钥bytes
        byte[] encPubKey = new BASE64Decoder().decodeBuffer(pubKeyBase64);

        // 创建 已编码的公钥规格
        X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(encPubKey);

        // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(encPubKeySpec);
    }

    /**
     * 根据base64创建私钥对象
     *
     * @param priKeyBase64 base私钥字符串
     * @return 返回公钥对象
     * @throws NoSuchAlgorithmException, IOException, InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String priKeyBase64) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        // 把 私钥的Base64文本 转换为已编码的 私钥bytes
        byte[] encPriKey = new BASE64Decoder().decodeBuffer(priKeyBase64);

        // 创建 已编码的私钥规格
        PKCS8EncodedKeySpec encPriKeySpec = new PKCS8EncodedKeySpec(encPriKey);

        // 获取指定算法的密钥工厂, 根据 已编码的私钥规格, 生成私钥对象
        return KeyFactory.getInstance(ALGORITHM_RSA).generatePrivate(encPriKeySpec);
    }

    /**
     * 根据公钥加密
     *
     * @param plainData 加密的数据
     * @param pubKey    公钥
     * @return 返回加密后的数据
     * @throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException 异常
     */
    public static byte[] encrypt(byte[] plainData, PublicKey pubKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        // 获取指定算法的密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);

        // 初始化密码器（公钥加密模型）
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        // 加密数据, 返回加密后的密文
        return cipher.doFinal(plainData);
    }

    /**
     * 根据私钥解密
     *
     * @param cipherData 解密数据
     * @param priKey     私钥
     * @return 返回解密后的数据
     * @throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException 异常
     */
    public static byte[] decrypt(byte[] cipherData, PrivateKey priKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 获取指定算法的密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);

        // 初始化密码器（私钥解密模型）
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        // 解密数据, 返回解密后的明文
        return cipher.doFinal(cipherData);
    }

    /**
     * 读取p12文件获取公钥私钥
     *
     * @param filePath 文件路径
     * @param password 密码
     * @throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException
     * @return 返回公钥和私钥map
     */
    public static HashMap<String, Key> readP12Cert(String filePath, String password) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException {
        KeyStore ks = KeyStore.getInstance(ALGORITHM_PKCS12);
        FileInputStream fis = new FileInputStream(filePath);

        char[] nPassword = null;
        if (password == null) {
            nPassword = null;
        } else {
            nPassword = password.toCharArray();
            ks.load(fis, nPassword);
            fis.close();
        }
        Enumeration<String> aliases = ks.aliases();
        String keyAlias = null;
        if (aliases.hasMoreElements()) {
            keyAlias = aliases.nextElement();
        }

        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        HashMap<String,Key> hashMap = new HashMap<>(2);
        //todo
        hashMap.put("prikey",prikey);
        hashMap.put("pubkey",pubkey);
        return hashMap;
    }

}