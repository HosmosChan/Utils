package com.hosmos.utils;

import java.security.interfaces.*;

/**
 * @author Hosmos
 * @version 2.0
 * @Description: 接口
 * @date 2018年10月08日 09:51:15
 */
public class CodeUtils {
    /**
     * RSA加密方式；通过公钥对数据进行加密(接口为RSAEncode("message", "publicKey"))
     *
     * @param message   原始数据
     * @param publicKey 公钥
     * @return 公钥加密后的数据
     */
    public static String RSAEncode(String message, String publicKey) throws Exception {
        RSAPublicKey RSAPublicKey = RSAUtils.getPublicKey(publicKey);
        return RSAUtils.encode(message, RSAPublicKey);
    }

    /**
     * RSA解密方式；通过私钥对数据进行解密(接口为RSADecode("message", "privateKey"))
     *
     * @param message    被公钥加密后的数据
     * @param privateKey 私钥(私钥为开发者或唯一管理员所掌握的密钥)
     * @return 原始数据
     */
    public static String RSADecode(String message, String privateKey) throws Exception {
        RSAPrivateKey RSAPrivateKey = RSAUtils.getPrivateKey(privateKey);
        return RSAUtils.encode(message, RSAPrivateKey);
    }

    /**
     * DES3加密方式；通过密钥对数据进行加密(接口为DES3Encode("message", "key"))
     *
     * @param message 原始数据
     * @param key     密钥
     * @return 密钥加密后的数据
     */
    public static String DES3Encode(String message, String key) {
        try {
            return DES3Utils.des3Encode(key, message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES3解密方式；通过密钥对数据进行解密(接口为DES3Decode("message", "key"))
     *
     * @param message 密钥解密后的数据
     * @param key     密钥
     * @return 原始数据
     */
    public static String DES3Decode(String message, String key) {
        return DES3Utils.des3Decode(key, message);
    }

    /**
     * AES加密方式；通过密钥对数据进行加密(接口为AESEncode("message", "key"))
     *
     * @param message 原始数据
     * @param key     密钥
     * @return 密钥加密后的数据
     */
    public static String AESEncode(String message, String key) throws Exception {
        return AESUtils.encode(message, key);
    }

    /**
     * AES解密方式；通过密钥对数据进行解密(接口为AESDecode("message", "key"))
     *
     * @param message 密钥解密后的数据
     * @param key     密钥
     * @return 原始数据
     */
    public static String AESDecode(String message, String key) throws Exception {
        return AESUtils.decode(message, key);
    }

    /**
     * Hash加密方式；通过密钥对数据进行加密(接口为HashEncode("message", "key", "type"))
     *
     * @param message 原始数据
     * @param key     密钥(若为非Hmac加密则不会使用，可随意输入)
     * @param type    加密方式(可为SHA-1、SHA-224、SHA-256、SHA-384、SHA-512、HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512、HmacMD5)
     * @return 密钥加密后的数据
     */
    public static String HashEncode(String message, String key, String type) throws Exception {
        if (type.contains("HmacSHA") || type.contains("HmacMD5")) {
            return HashUtils.hmacEncode(message, key, type);
        } else if (type.contains("SHA-")) {
            return HashUtils.shaEncode(message, type);
        } else {
            return null;
        }
    }

    /**
     * MD5加密方式；对数据进行MD5加密(接口为MD5Encode("message"))
     *
     * @param message 原始数据
     * @return 加密后的数据
     */
    public static String MD5Encode(String message) {
        return MD5Utils.MD5Encode(message, "UTF-8");
    }
}