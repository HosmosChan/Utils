package com.hosmos.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Hosmos
 * @version 2.0
 * @Description: DES加解密：接口des3Encode & des3Decode
 * @date 2018年9月30日 15:47:20
 */
public class DES3Utils {
    /**
     * 将24位密钥转换成十六进制字符串
     *
     * @param key 24位密钥
     * @return 16进制字符串
     */
    public static byte[] hex(String key) {
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 3DES加密
     *
     * @param key    24位密钥
     * @param srcStr 欲加密的字符串
     * @return 加密后的数据
     */
    public static String des3Encode(String key, String srcStr) {
        byte[] keybyte = hex(key);
        byte[] src = srcStr.getBytes();
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            String pwd = Base64.encodeBase64String(c1.doFinal(src));
            //return c1.doFinal(src);
            //在单一方面的加密或解密
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param key    24位密钥
     * @param desStr 欲解密的字符串
     * @return 解密后的数据
     */
    public static String des3Decode(String key, String desStr) {
        Base64 base64 = new Base64();
        byte[] keybyte = hex(key);
        byte[] src = base64.decode(desStr);
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            //解密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            String pwd = new String(c1.doFinal(src));
            //return c1.doFinal(src);
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}