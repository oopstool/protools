package com.github.oopstool.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import com.github.oopstool.string.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SecureUtilsTest {

    @Test
    public void encryptString() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM_SHA);
        assert encryptString.length()==40;
        String encryptString0 = SecureUtils.encryptStringWithSalt("111111", SecureUtils.ALGORITHM_SHA,"ddd");
        assert encryptString0.length()==40;
        String encryptString1 = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM_MD5);
        assert encryptString1.length()==32;
        String encryptString2 = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM__SHA256);
        assert encryptString2.length()==64;
    }

    @Test
    public void encryptByMD5() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptByMD5("111111");
        assert encryptString.length()==32;
        String encryptString1 = SecureUtils.encryptByMD5("111111","sdf");
        assert encryptString1.length()==32;
    }

    @Test
    public void encryptBySHA() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptBySHA("111111");
        assert encryptString.length()==40;
        String encryptString1 = SecureUtils.encryptBySHA("111111","243esdf");
        assert encryptString1.length()==40;
    }

    @Test
    public void encryptBySHA256() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptBySHA256("111111");
        assert encryptString.length()==64;
        String encryptString1 = SecureUtils.encryptBySHA256("111111","sdae");
        assert encryptString1.length()==64;
    }

    @Test
    public void generateKeyPair() throws Exception {
        KeyPair keyPair = SecureUtils.generateKeyPair();
        Assert.assertNotNull(keyPair);
        Assert.assertNotNull(keyPair.getPrivate());
        Assert.assertNotNull(keyPair.getPublic());
    }

    @Test
    public void keyForEncodedBase64() throws Exception {
        KeyPair keyPair = SecureUtils.generateKeyPair();
        String pri = SecureUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecureUtils.keyForEncodedBase64(keyPair.getPublic());
        Assert.assertNotNull(pri);
        Assert.assertNotNull(pub);


    }

    @Test
    public void getPublicKey() throws Exception {
        KeyPair keyPair = SecureUtils.generateKeyPair();
        String pri = SecureUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecureUtils.keyForEncodedBase64(keyPair.getPublic());
        PublicKey publicKey = SecureUtils.getPublicKey(pub);
        Assert.assertNotNull(publicKey);
    }

    @Test
    public void getPrivateKey() throws Exception {
        KeyPair keyPair = SecureUtils.generateKeyPair();
        String pri = SecureUtils.keyForEncodedBase64(keyPair.getPrivate());
        String pub = SecureUtils.keyForEncodedBase64(keyPair.getPublic());
        PrivateKey privateKey = SecureUtils.getPrivateKey(pri);
        Assert.assertNotNull(privateKey);
    }

    @Test
    public void encrypt() throws Exception {
        KeyPair keyPair = SecureUtils.generateKeyPair();
        byte[] helloWords = SecureUtils.encrypt(StringUtils.bytes("helloWord"), keyPair.getPublic());
        System.out.println(StringUtils.str(helloWords, StandardCharsets.UTF_8));
        Assert.assertNotNull(helloWords);
        byte[] decrypt = SecureUtils.decrypt(helloWords, keyPair.getPrivate());
        String str = StringUtils.str(decrypt, StandardCharsets.UTF_8);
        System.out.println(StringUtils.str(decrypt, StandardCharsets.UTF_8));
        assert "helloWord".equals(str);
    }



    @Test
    public void readP12Cert() {
    }

}