package com.github.oopstool.security;

import com.github.oopstool.string.StringUtils;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.junit.Assert;
import org.junit.Test;

public class SecurityUtilsTest {

    @Test
    public void encryptString() throws NoSuchAlgorithmException {
        String encryptString = SecurityUtils.encryptString("111111", SecurityUtils.ALGORITHM_SHA);
        assert encryptString.length() == 40;
        String encryptString0 = SecurityUtils.encryptStringWithSalt("111111", SecurityUtils.ALGORITHM_SHA, "ddd");
        assert encryptString0.length() == 40;
        String encryptString1 = SecurityUtils.encryptString("111111", SecurityUtils.ALGORITHM_MD5);
        assert encryptString1.length() == 32;
        String encryptString2 = SecurityUtils.encryptString("111111", SecurityUtils.ALGORITHM__SHA256);
        assert encryptString2.length() == 64;
    }

    @Test
    public void encryptByMD5() throws NoSuchAlgorithmException {
        String encryptString = SecurityUtils.encryptByMD5("111111");
        assert encryptString.length() == 32;
        String encryptString1 = SecurityUtils.encryptByMD5("111111", "sdf");
        assert encryptString1.length() == 32;
    }

    @Test
    public void encryptBySHA() throws NoSuchAlgorithmException {
        String encryptString = SecurityUtils.encryptBySHA("111111");
        assert encryptString.length() == 40;
        String encryptString1 = SecurityUtils.encryptBySHA("111111", "243esdf");
        assert encryptString1.length() == 40;
    }

    @Test
    public void encryptBySHA256() throws NoSuchAlgorithmException {
        String encryptString = SecurityUtils.encryptBySHA256("111111");
        assert encryptString.length() == 64;
        String encryptString1 = SecurityUtils.encryptBySHA256("111111", "sdae");
        assert encryptString1.length() == 64;
    }

    @Test
    public void generateKeyPair() throws Exception {
        KeyPair keyPair = SecurityUtils.generateKeyPair();
        Assert.assertNotNull(keyPair);
        Assert.assertNotNull(keyPair.getPrivate());
        Assert.assertNotNull(keyPair.getPublic());
    }

    @Test
    public void keyForEncodedBase64() throws Exception {
        KeyPair keyPair = SecurityUtils.generateKeyPair();
        String pri = SecurityUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecurityUtils.keyForEncodedBase64(keyPair.getPublic());
        Assert.assertNotNull(pri);
        Assert.assertNotNull(pub);


    }

    @Test
    public void getPublicKey() throws Exception {
        KeyPair keyPair = SecurityUtils.generateKeyPair();
        String pri = SecurityUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecurityUtils.keyForEncodedBase64(keyPair.getPublic());
        PublicKey publicKey = SecurityUtils.getPublicKey(pub);
        Assert.assertNotNull(publicKey);
    }

    @Test
    public void getPrivateKey() throws Exception {
        KeyPair keyPair = SecurityUtils.generateKeyPair();
        String pri = SecurityUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecurityUtils.keyForEncodedBase64(keyPair.getPublic());
        PrivateKey privateKey = SecurityUtils.getPrivateKey(pri);
        Assert.assertNotNull(privateKey);
    }

    @Test
    public void encrypt() throws Exception {
        KeyPair keyPair = SecurityUtils.generateKeyPair();
        byte[] helloWords = SecurityUtils.encrypt(StringUtils.bytes("helloWord"), keyPair.getPublic());
        System.out.println(StringUtils.str(helloWords, StandardCharsets.UTF_8));
        Assert.assertNotNull(helloWords);
        byte[] decrypt = SecurityUtils.decrypt(helloWords, keyPair.getPrivate());
        String str = StringUtils.str(decrypt, StandardCharsets.UTF_8);
        System.out.println(StringUtils.str(decrypt, StandardCharsets.UTF_8));
        assert "helloWord".equals(str);
    }


    @Test
    public void readP12Cert() {
    }

}