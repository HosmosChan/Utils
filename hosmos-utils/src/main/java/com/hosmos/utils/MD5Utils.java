package com.hosmos.utils;

import java.security.MessageDigest;

/**
 * @author Hosmos
 * @version 1.0
 * @Description: MD5加密，接口MD5Encode
 * @date 2018年9月30日 15:47:20
 */
public class MD5Utils {
    /**
     * 将字节数组转化成16进制字符串
     *
     * @param b 字符集
     * @return 16进制字符串
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) resultSb.append(byteToHexString(b[i]));
        System.err.println(resultSb.toString());
        return resultSb.toString();
    }

    /**
     * 将字节进行变换转化成16进制乱码
     *
     * @param b 字符集
     * @return 重新排序后的字符集
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n += 256;
        int d1 = n % 16;
        int d2 = n / 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 将字节进行变换转化成16进制乱码
     *
     * @param content     加密数据
     * @param charsetname 编码方式
     * @return 加密后的16进制数据
     */
    public static String MD5Encode(String content, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(content);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * 定义16进制
     */
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) {
        String content = "123456";
        System.out.println(MD5Encode(content, "UTF-8"));
    }
}