package com.hosmos.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.Base64;

/**
 * @author Hosmos
 * @version 1.0
 * @Description: RSA加解密，接口encode & decode
 * @date 2018年9月30日 15:47:20
 */
public class RSAUtils {
    /**
     * 固定私钥与匹配的公钥
     */
    private static final String privateKeyDef = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDqwmQ2NK8Ks6sYJVDwWO9MzvNkaP4/5YX27xko1AHp47AVvRMIsYCpmqTBeV4Y/Hncjg3EDx3AogbIKdjqISkv4W5qrbYUOnNpOlC8s7p7boHCzLfcMx4aiHhqeSHyEMk6lBjvNPaSCyCOdE8bmeZY8Dr1TG8pFQPHrcb7XVee6RG/4zg6Giax6uUjqYXHD9zpiYKmFJ6MG3aOp1IWnHloQg+LEgkRWVeWFndXDw2gJiPl73efQKVDIP77gtdXQtEsHspQvOKxFYDNeW+R3Pp2bhF7+0NnH79W36pNr/695Go3Ak9vDeeDH/euPerC91m24hIvw7oTGMqbMEVdAc9VAgMBAAECggEBALeJ/5s5mVi+m5enJR6kCYN9kWEBjTYblp820+7NwilAcByu1EDJthPj6cexwdW7HvNGxJByWQqQIsk/nm1+O5tJnnAdG+u4vx6YDyNZSQTTovPP5jdsZ67K34ou5lFAunLn8o6iU9xegredS3QC4MrMFuxNM+NkoK3uuVEw6x47DuYihQAgavSyN0ntxhVJ+EnpJ7MIsR9DnzzCw4m/U+g3Y2Yb/5KkyrWF75SQ9vCIMN73FRIsDAD0m+lmBUfO+FMHLppql27otkaowyh1MpyXxzI58hxHEnezI3ZcFcVkpRASWlugU4fbt5HdY2KsZcTZRQga+SZyZYXLadAruckCgYEA9hC6yLzKnpwY4QrE3sc8isNyDXQuop1b1rl43f3K6EAj6pTnF4H1ncydv+lKDiRPVTlRYagxt7wSHK5hmEqMk0zUw5z1V2Jojln6digkhURBMo1MVQdItlPeJU9D2tCl8dNu1VDAYjUPkxeeHRjzvz/81VS+3/YE14kIOSvMLmMCgYEA9DzOREnP0iooz8kMECLPKoTQaZeza71mxI226SXDwk1xje0bhEIOHM1LpPrfQD+Jxwi/1y91+wCnouNonrMTwGpUFJ+6dvHnWQnE7uonR7sU627cy6EJImEJwfDsuWiM6ObhrUVAH/gfH/YeWmtaBurvJBZI8fgB+RXjlJVEfOcCgYALXXxzFx04tDcF9exf6c3nDt3utyYMRr5eLZTik/1SOtwt0pDV6h/BGRlUKt9LxeuQISScIRuiw+IGRxuk4BScHRlaq549WgNAR/GkkXgWW6+zOQmEh+CY40jIal/U9HwlrkFZQvCP2KmOfrHD4Eop1DfGVrK2P3O7zM1/boFwxwKBgCCIwQlDs9VRHGKha2Py3xnQ21pVhOcDMd9Y6VahWzRU0J2RSisSCYM4razqZjbOv/GtbrChd9vZuHrwRA1v2V+2SkD9Df+uNZ0cugkBU/4wI5dBC1RsvSTBPGWDTGVeUfbsWn+US/h2Ot8tD9oHltR5EyYBefIRkWG20rwGLjtdAoGBAOYbvWHxpKnNgV4HlnxTtmjMbxF/XKQW39U9KNVG2Ad1n+VYx2wMWJNeuqINgNaeoYf6/ISR3ZKCn6RjBceK+v3dc0LT0wpvW3UlOy6U3SPxz3SQxpIIafDPEjHO87ucVI7f4Hz9+BT0eklangYyi6pzEdfckK9utCqI+p7Xga7U";
    private static final String publicKeyDef = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6sJkNjSvCrOrGCVQ8FjvTM7zZGj+P+WF9u8ZKNQB6eOwFb0TCLGAqZqkwXleGPx53I4NxA8dwKIGyCnY6iEpL+Fuaq22FDpzaTpQvLO6e26Bwsy33DMeGoh4ankh8hDJOpQY7zT2kgsgjnRPG5nmWPA69UxvKRUDx63G+11XnukRv+M4OhomserlI6mFxw/c6YmCphSejBt2jqdSFpx5aEIPixIJEVlXlhZ3Vw8NoCYj5e93n0ClQyD++4LXV0LRLB7KULzisRWAzXlvkdz6dm4Re/tDZx+/Vt+qTa/+veRqNwJPbw3ngx/3rj3qwvdZtuISL8O6ExjKmzBFXQHPVQIDAQAB";

    public static class RsaKeyPair {
        private String publicKey = "";
        private String privateKey = "";

        /**
         * 根据密钥字符串获取公钥和私钥
         *
         * @param publicKey  公钥字符串
         * @param privateKey 私钥字符串
         * @return 公钥 & 私钥
         */
        private RsaKeyPair(String publicKey, String privateKey) {
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
     * @return 公钥和私钥对
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
     * @param pubOrPriKey 公钥或私钥
     * @return 加密后的数据
     */
    public static String encode(String content, Key pubOrPriKey) throws Exception {
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubOrPriKey);
        byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 对加密过的数据进行解密
     *
     * @param content     解密数据
     * @param pubOrPriKey 公钥或私钥
     * @return 解密后的数据
     */
    public static String decode(String content, Key pubOrPriKey) throws Exception {
        Cipher cipher = null;
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubOrPriKey);
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(result);
    }

    public static void main(String[] args) throws Exception {
        String text = "123456";
        /*RsaKeyPair keyPair = generaterKeyPair();
        System.out.println("PrivateKey:" + keyPair.getPrivateKey());
        System.out.println("PublicKey:" + keyPair.getPublicKey());*/
        String signTest = sign(text, privateKeyDef, true);
        System.err.println(signTest);
        boolean verifyTest = verify(text, signTest, publicKeyDef, true);
        System.out.println(verifyTest);
    }
}