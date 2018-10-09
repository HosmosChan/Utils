package com.gemdale.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;

public class DES3Utils {
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

    /**
     * base64加密
     *
     * @param data 欲加密的字节数组
     * @return 加密后的数据
     */
    public static String base64Encode(byte[] data) {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);
        int end = len - 3;
        int i = start;
        int n = 0;
        while (i <= end) {
            int d = (data[i] & 0xFF) << 16 | (data[(i + 1)] & 0xFF) << 8 | data[(i + 2)] & 0xFF;
            buf.append(legalChars[(d >> 18 & 0x3F)]);
            buf.append(legalChars[(d >> 12 & 0x3F)]);
            buf.append(legalChars[(d >> 6 & 0x3F)]);
            buf.append(legalChars[(d & 0x3F)]);
            i += 3;
            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }
        if (i == start + len - 2) {
            int d = (data[i] & 0xFF) << 16 | (data[(i + 1)] & 0xFF) << 8;
            buf.append(legalChars[(d >> 18 & 0x3F)]);
            buf.append(legalChars[(d >> 12 & 0x3F)]);
            buf.append(legalChars[(d >> 6 & 0x3F)]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = (data[i] & 0xFF) << 16;
            buf.append(legalChars[(d >> 18 & 0x3F)]);
            buf.append(legalChars[(d >> 12 & 0x3F)]);
            buf.append("==");
        }
        return buf.toString();
    }

    public static byte[] base64Decode(String s) {
        s = s.replace("-", "+").replace("_", "/");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            decode(s, bos);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        byte[] decodedBytes = bos.toByteArray();
        try {
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return decodedBytes;
    }
    private static int decode(char c) {
        if ((c >= 'A') && (c <= 'Z')) {
            return c - 'A';
        }
        if ((c >= 'a') && (c <= 'z')) {
            return c - 'a' + 26;
        }
        if ((c >= '0') && (c <= '9')) {
            return c - '0' + 26 + 26;
        }
        switch (c) {
            case '+':
                return 62;
            case '/':
                return 63;
            case '=':
                return 0;
        }
        throw new RuntimeException("unexpected code: " + c);
    }
    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;
        int len = s.length();
        while (true) {
            while (i < len && s.charAt(i) <= ' ')
                i++;
            if (i == len)
                break;
            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));
            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=')
                break;
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=')
                break;
            os.write(tri & 255);
            i += 4;
        }
    }

    /**
     * 3DES加密
     *
     * @param key    24位密钥
     * @param srcStr 欲加密的字符串
     * @return 加密后的数据
     */
    public static String des3Encode(String srcStr, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec("01234567".getBytes());
        cipher.init(1, deskey, ips);
        byte[] encryptData = cipher.doFinal(srcStr.getBytes("UTF-8"));
        return base64Encode(encryptData);
    }

    public static String des3Decode(String encryptText, String secretKey) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec("01234567".getBytes());
        cipher.init(2, deskey, ips);
        byte[] decryptData = cipher.doFinal(base64Decode(encryptText));
        return new String(decryptData, "UTF-8");
    }
}