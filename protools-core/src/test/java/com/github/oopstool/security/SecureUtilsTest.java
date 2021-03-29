package com.github.oopstool.security;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class SecureUtilsTest {

    @Test
    public void encryptString() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM_SHA);
        assert encryptString.length()==40;
        String encryptString1 = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM_MD5);
        assert encryptString1.length()==32;
        String encryptString2 = SecureUtils.encryptString("111111", SecureUtils.ALGORITHM__SHA256);
        assert encryptString2.length()==64;
    }

    @Test
    public void encryptByMD5() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptByMD5("111111");
        assert encryptString.length()==32;
    }

    @Test
    public void encryptBySHA() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptBySHA("111111");
        assert encryptString.length()==40;
    }

    @Test
    public void encryptBySHA256() throws NoSuchAlgorithmException {
        String encryptString = SecureUtils.encryptBySHA256("111111");
        assert encryptString.length()==64;
    }
}