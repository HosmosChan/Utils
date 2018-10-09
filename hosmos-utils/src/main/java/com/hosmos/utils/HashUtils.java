package com.hosmos.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Hosmos
 * @version 2.0
 * @Description: SHA加密：接口shaEncode；Hmac加密：接口hmacEncode
 * @date 2018年10月03日 00:20:10
 */
public class HashUtils {
    /**
     * 对数据进行SHA1加密
     *
     * @param content SHA方式加密数据
     * @param type    SHA加密类型(可用SHA-1、SHA-224、SHA-256、SHA-384、SHA-512)
     * @return 加密后的数据
     */
    public static String shaEncode(String content, String type) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance(type);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = content.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 使用 HMAC 签名加密方法对data进行签名
     *
     * @param data 欲加密的字符串
     * @param key  密钥
     * @param type Hmac加密类型(可用HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512、HmacMD5)
     * @return 加密后的字符串
     */
    public static String hmacEncode(String data, String key, String type) {
        byte[] result = null;
        try {
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), type);
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(type);
            //用给定密钥初始化 Mac 对象
            mac.init(signinKey);
            //完成 Mac 操作
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encodeBase64(rawHmac);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println(e.getMessage());
        }
        if (null != result) {
            return new String(result);
        } else {
            return null;
        }
    }
}