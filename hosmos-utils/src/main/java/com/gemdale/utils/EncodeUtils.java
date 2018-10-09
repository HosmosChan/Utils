package com.gemdale.utils;

import com.google.gson.Gson;
import java.util.HashMap;

public class EncodeUtils {
    public String messageEncode(String mobile, String password) throws Exception {
        Gson gson = new Gson();
        Gemdale gemdale = new Gemdale();
        HashMap head = new HashMap();
        if ((head.containsKey("userId")) && (head.containsKey("sessionId"))) {
            head.put("sessionId", head.get("sessionId"));
            head.put("userId", head.get("userId"));
        } else {
            head.put("sessionId", HttpUtil.SESSION_ID);
            head.put("userId", HttpUtil.USER_ID);
        }
        long timestamp = System.currentTimeMillis();
        head.put("timestamp", Long.valueOf(timestamp));
        head.put(
                "token",
                MD5Utils.MD5Encode(HttpUtil.SESSION_ID + HttpUtil.DES_KEY
                        + HttpUtil.USER_ID + timestamp));
        head.put("macId", "");
        head.put("deviceInfo", "ALP-AL00|8.0.0[2.9.1]home");
        gemdale.head = head;
        gemdale.appVersion = "2.9.1";
        gemdale.password = password;
        gemdale.mobile = mobile;
        gemdale.channel = "home";
        gemdale.mobileVersion = "ALP-AL00|8.0.0";
        gemdale.type = "01";
        gemdale.content = password;
        gemdale.mobileOs = "01";
        String params222 = DES3Utils.des3Encode(gson.toJson(gemdale),
                "AOSw9O!^lgquMP$nZDvo%koJ");
        return params222;
    }
    public static void main(String args[]) throws Exception {
        EncodeUtils codeUtils = new EncodeUtils();
        System.out.println(codeUtils.messageEncode("18565693063", "a111111"));
    }
    public String messageDecode(String jsonResponse) throws Exception {
        String jsonResponseUnDecode = DES3Utils.des3Decode(jsonResponse, "AOSw9O!^lgquMP$nZDvo%koJ");
        System.out.print("*******messageDecode*******" + jsonResponseUnDecode + "*******messageDecode*******");
        return jsonResponseUnDecode;
    }
}