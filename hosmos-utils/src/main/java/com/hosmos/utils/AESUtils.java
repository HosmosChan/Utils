package com.hosmos.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Hosmos
 * @version 1.0
 * @Description: AES加解密，接口encrypt & decrypt
 * @date 2018年9月30日 15:47:20
 */
public class AESUtils {
    /**
     * 固定密钥
     */
    private static final String generaterKeyDef = "FkI6ZXjtWHIPW8eEl1Z1ag==";

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 生成密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generaterKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
        keygen.init(128, new SecureRandom()); // 16 字节 == 128 bit
        //keygen.init(128, new SecureRandom(seedStr.getBytes())); // 随机因子一样，生成出来的密钥会一样
        SecretKey secretKey = keygen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 根据密钥字符串获取密钥
     *
     * @param secretKeyStr 密钥字符串
     * @return (SecretKeySpec类型)密钥
     */
    public static SecretKeySpec getSecretKeySpec(String secretKeyStr) {
        byte[] secretKey = Base64.getDecoder().decode(secretKeyStr);
        return new SecretKeySpec(secretKey, ALGORITHM);
    }

    /**
     * 对数据进行加密
     *
     * @param content   加密数据
     * @param secretKey 密钥
     * @throws Exception
     */
    public static String encrypt(String content, String secretKey) throws Exception {
        Key key = getSecretKeySpec(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 对数据进行解密
     *
     * @param content   解密数据
     * @param secretKey 密钥
     * @throws Exception
     */
    public static String decrypt(String content, String secretKey) throws Exception {
        Key key = getSecretKeySpec(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        String content = "123456";
        String gen = generaterKey();
        System.out.println(gen);
        System.err.println(encrypt(content, gen));
        System.err.println(decrypt(encrypt(content, gen), gen));
    }
}