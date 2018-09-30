package com.hosmos.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Hosmos
 * @version 1.0
 * @Description: RSA加解密，接口encrypt & decrypt
 * @date 2018年9月30日 15:47:20
 */
public class RSAUtils {
    /**
     * 固定私钥与匹配的公钥
     */
    private static final String privateKeyDef = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPIlraI/Mdhw2WQG\n" +
            "tR3yYytoqddKntDKUoBU+311s9XLHR7tfyAKsALbzyXF5VyNRoiBV6mbjViTP28n\n" +
            "9ymPFPo2kU6sv2qkOJdNWEOGFTVi9g3bRyz+vSH9YIMRMc/m6NVus/hYECY+IXqP\n" +
            "1+y6mPmWQyUXTufJL5d08J9Yhq/lAgMBAAECgYAVF/0pLhIchbAaS49JfYzwAh0R\n" +
            "eRmenJFVRpHl2vQAgsIVqKCzXNr6VMWVx8h0KIZla8cEKl3Ewob7GuMoBqPQAMGp\n" +
            "iNtuiJ5/kdrqN49wnBhupeXj1KHKIcqutqPfU7jIuasENE3Gk6xPJnR9I6QlLKSy\n" +
            "My1cb6b3/POH+/4FfQJBAPtA/zAH8slKdL2FMTTEZ/eYMOabt783B9eBYejMNb2X\n" +
            "W5m8qDK27kg/SIyXE+UiF6OCDNyT1aYI8HRBVN36AdcCQQD2uKTAm7Gb1jndEIvF\n" +
            "mt93bVhMwlbd9z9uEx979INx6WD4LZ8v4xyKzqcDw0Oj8lbngT9WPP5ziJoufUuq\n" +
            "fByjAkEAnw8xviV6BQZ2yzgTw5UHQI5/fq5b79iVxU9qPWdyKhk06ymszZZTWTd+\n" +
            "5UoLVUZ08pU1MTsByT/lgaOOZ4yLNwJBAJa5RIUaWI2weRcRNaJZs+080rXJ/9GI\n" +
            "a6quagyK2wZrIb9b96UEpPoztxp4Xsk4kljJv8zKZFRmfnKqPuB/A+sCQF/8aDmi\n" +
            "fTjhKlEiaovfrVqXkCtXQkcU+a8aWe63TqXTVojYzl++4WMhiLTrBXInPd5cqiZl\n" +
            "QEOP4BRLaXDHfuM=";
    private static final String publicKeyDef = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDyJa2iPzHYcNlkBrUd8mMraKnX\n" +
            "Sp7QylKAVPt9dbPVyx0e7X8gCrAC288lxeVcjUaIgVepm41Ykz9vJ/cpjxT6NpFO\n" +
            "rL9qpDiXTVhDhhU1YvYN20cs/r0h/WCDETHP5ujVbrP4WBAmPiF6j9fsupj5lkMl\n" +
            "F07nyS+XdPCfWIav5QIDAQAB";

    public static class RsaKeyPair {
        private String publicKey = "";
        private String privateKey = "";

        public RsaKeyPair(String publicKey, String privateKey) {
            super();
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }

    private static final String ALGORITHM = "RSA";
    private static final String ALGORITHMS_SHA1WithRSA = "SHA1WithRSA";
    private static final String ALGORITHMS_SHA256WithRSA = "SHA256WithRSA";
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static String getAlgorithms(boolean isRsa2) {
        return isRsa2 ? ALGORITHMS_SHA256WithRSA : ALGORITHMS_SHA1WithRSA;
    }

    /**
     * 生成密钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RsaKeyPair generaterKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
        SecureRandom random = new SecureRandom();
        //SecureRandom random = new SecureRandom(seedStr.getBytes()); // 随机因子一样，生成出来的密钥会一样
        // 512位已被破解，用1024位,最好用2048位
        keygen.initialize(2048, random);
        // 生成密钥对
        KeyPair keyPair = keygen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return new RsaKeyPair(publicKeyStr, privateKeyStr);
    }

    /**
     * 根据公钥字符串获取公钥
     *
     * @param publicKey 公钥字符串
     * @return (RSAPublicKey类型)公钥
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    /**
     * 根据私钥字符串获取私钥
     *
     * @param privateKey 私钥
     * @return (RSAPrivateKey类型)私钥
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param content    加密数据
     * @param privateKey 私钥
     * @param isRsa2     加密方式(ALGORITHMS_SHA256 : ALGORITHMS_SHA1WithRSA)
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    public static String sign(String content, String privateKey, boolean isRsa2) throws Exception {
        PrivateKey priKey = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(getAlgorithms(isRsa2));
        signature.initSign(priKey);
        signature.update(content.getBytes(DEFAULT_CHARSET));
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }

    /**
     * 校验公钥数字签名
     *
     * @param content   加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @param isRsa2    加密方式(ALGORITHMS_SHA256 : ALGORITHMS_SHA1WithRSA)
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(String content, String sign, String publicKey, boolean isRsa2) throws Exception {
        PublicKey pubKey = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(getAlgorithms(isRsa2));
        signature.initVerify(pubKey);
        signature.update(content.getBytes(DEFAULT_CHARSET));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * 对数据进行加密
     *
     * @param content     加密数据
     * @param pubOrPrikey 公钥或私钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, Key pubOrPrikey) throws Exception {
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubOrPrikey);
        byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 对加密过的数据进行解密
     *
     * @param content     解密数据
     * @param pubOrPrikey 公钥或私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, Key pubOrPrikey) throws Exception {
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubOrPrikey);
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        RsaKeyPair gen = generaterKeyPair();
        RSAPublicKey publicKey = getPublicKey(gen.publicKey);
        RSAPrivateKey privateKey = getPrivateKey(gen.privateKey);
        String content = "123456";
        System.out.println(encrypt(content, privateKey));
        System.out.println(decrypt(encrypt(content, privateKey), publicKey));
        System.err.println(verify(content, sign(content, gen.privateKey, false), gen.publicKey, false));
    }
}