package com.gemdale.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 后台测试执行报事请求
 *
 * @author chenhuayang
 */
public class workJmeterTest {
    public void gsontoString(String gsonString) {
        System.out.print("************************" + gsonString + "************************");
        JsonElement element = new JsonParser().parse(gsonString);
        JsonObject root = element.getAsJsonObject();
        String resultCode = root.get("code").getAsString();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(resultCode);
    }

    public static void main(String[] args) throws Exception {
        EncodeUtils test01 = new EncodeUtils();
        workJmeterTest t2 = new workJmeterTest();
        t2.gsontoString(test01.testWork(test01.messageEncode(test01.testLogin("18565693063", "a111111"))));
    }
}