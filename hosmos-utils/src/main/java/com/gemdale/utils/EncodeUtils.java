package com.gemdale.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class EncodeUtils {
    String response11 = "null response11";

    public String getResponse11() {
        return this.response11;
    }

    public void setResponse11(String response11) {
        this.response11 = response11;
    }

    /**
     * 通过传到服务器里获取返回值的方法
     *
     * @param params     返回的加密数据
     * @param defaultURL 路径地址
     * @author chenhuayang
     */
    private String onSetting2(String params, String defaultURL)
            throws IOException {
        System.out.println("----params-----" + params + "-------params----");
        System.out.println("---defaultURL------" + defaultURL + "-----defaultURL------");
        try {
            String params1 = DES3Utils.des3Encode(params, "AOSw9O!^lgquMP$nZDvo%koJ");
            this.response11 = HttpClientUtil.sendJsonRequest(defaultURL, params1, "haha", true, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setResponse11(this.response11);
        System.out.println("----response11-----:" + this.response11 + "----response11-----:");
        System.out.println("*********************");
        System.out.println("&&&&&&&&&&" + getResponse11() + "&&&&&&&&&&&&&&&&&");
        return this.response11;
    }

    /**
     * 通过传到服务器里获取返回值(仅仅显示Response)的方法
     *
     * @param params     返回的加密数据
     * @param defaultURL 路径地址
     * @author chenhuayang
     */
    private String onSettingOnlyResponse(String params, String defaultURL) throws IOException {
        try {
            String params1 = DES3Utils.des3Encode(params, "AOSw9O!^lgquMP$nZDvo%koJ");
            String jsonResponse = null;
            org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
            httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
            httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
            HttpPost postMethod = new HttpPost(defaultURL);
            StringEntity entity = new StringEntity(params1, "UTF-8");
            entity.setContentEncoding(new BasicHeader("Content-Encoding", Consts.UTF_8.toString()));
            entity.setContentType(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            postMethod.setEntity(entity);
            postMethod.getParams().setParameter("http.useragent", "enjoy_link|ghome|3.0.0");
            HttpResponse response = httpClient.execute(postMethod);
            if (200 == response.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = response.getEntity();
                jsonResponse = EntityUtils.toString(httpEntity);
                EntityUtils.consume(response.getEntity());
            }
            if ((jsonResponse == null) || (jsonResponse.trim().length() < 1)) {
            } else {
                jsonResponse = true ? DES3Utils.des3Decode(jsonResponse, "AOSw9O!^lgquMP$nZDvo%koJ") : jsonResponse;
            }
            this.response11 = jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        setResponse11(this.response11);
        return this.response11;
    }

    /**
     * Jmeter调用执行登录请求方法
     *
     * @param mobile   登录账号
     * @param password 密码
     * @author chenhuayang
     */
    public String loginEncode(String mobile, String password) throws Exception {
        Gson gson = new Gson();
        GemdaleLogin gemdale = new GemdaleLogin();
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
        head.put("token", MD5Utils.MD5Encode(HttpUtil.SESSION_ID + HttpUtil.DES_KEY + HttpUtil.USER_ID + timestamp));
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
        String params222 = DES3Utils.des3Encode(gson.toJson(gemdale), "AOSw9O!^lgquMP$nZDvo%koJ");
        return params222;
    }

    /**
     * 后台测试执行登录请求方法
     *
     * @param mobile   登录账号
     * @param password 密码
     * @author chenhuayang
     */
    public String testLogin(String mobile, String password) throws Exception {
        Gson gson = new Gson();
        GemdaleLogin gemdale = new GemdaleLogin();
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
        head.put("token", MD5Utils.MD5Encode(HttpUtil.SESSION_ID + HttpUtil.DES_KEY + HttpUtil.USER_ID + timestamp));
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
        String response22 = onSettingOnlyResponse(gson.toJson(gemdale), "http://10.243.3.18:8888/enjoylink/ghome/user/loginGhome.do");
        return response22;
    }

    /**
     * Jmeter调用执行报事请求方法
     *
     * @param jsonResponse 登录且加密后响应的数据
     * @author chenhuayang
     */
    public String workEncode(String jsonResponse) throws Exception {
        Gson gson = new Gson();
        GemdaleWork gemdale = new GemdaleWork();
        HashMap head = new HashMap();
        String jsonResponseDecode = messageDecode(jsonResponse);
        String JSON = jsonResponseDecode;
        JsonElement JS = new JsonParser().parse(JSON);
        String HeadJSON = gson.toJson(JS.getAsJsonObject().get("result"));
        JsonElement HeadJS = new JsonParser().parse(HeadJSON);
        gemdale.address = gson.toJson(HeadJS.getAsJsonObject().get("mainProjectName")).replace("\"", "") + gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingName")).replace("\"", "") + gson.toJson(HeadJS.getAsJsonObject().get("mainHouseName")).replace("\"", "");
        gemdale.projectName = gson.toJson(HeadJS.getAsJsonObject().get("projectName")).replace("\"", "");
        gemdale.source = "01";
        gemdale.serviceCode = "ET010205";
        gemdale.appointmentBeginTime = "2018-10-21 16:00:00";
        gemdale.longitude = "113.95476498005301";
        if (head.containsKey("userId")) {
            head.put("sessionId", HeadJS.getAsJsonObject().get("sessionId"));
            head.put("userId", HeadJS.getAsJsonObject().get("userId"));
        } else {
            head.put("sessionId", HeadJS.getAsJsonObject().get("sessionId"));
            head.put("userId", HeadJS.getAsJsonObject().get("userId"));
        }
        long timestamp = System.currentTimeMillis();
        head.put("timestamp", Long.valueOf(timestamp));
        head.put("token", HeadJS.getAsJsonObject().get("token"));
        head.put("macId", "");
        head.put("deviceInfo", "PLK-TL01H|6.0[2.9.5]home");
        gemdale.head = head;
        gemdale.pageSize = 20;
        gemdale.serviceName = "蚊虫消杀";
        gemdale.content = "hfdnhdhdbhdjd";
        gemdale.contactor = gson.toJson(HeadJS.getAsJsonObject().get("userName")).replace("\"", "");
        gemdale.reportUserType = gson.toJson(HeadJS.getAsJsonObject().get("certificateType")).replace("\"", "");
        gemdale.contactMobile = gson.toJson(HeadJS.getAsJsonObject().get("mobile")).replace("\"", "");
        gemdale.reportUserMobile = gson.toJson(HeadJS.getAsJsonObject().get("mobile")).replace("\"", "");
        gemdale.appointmentEndTime = "2018-10-21 17:00:00";
        gemdale.houseName = gson.toJson(HeadJS.getAsJsonObject().get("mainHouseName")).replace("\"", "");
        gemdale.buildingName = gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingName")).replace("\"", "");
        gemdale.projectCode = gson.toJson(HeadJS.getAsJsonObject().get("mainProjectCode")).replace("\"", "");
        gemdale.reportUserName = gson.toJson(HeadJS.getAsJsonObject().get("userName")).replace("\"", "");
        gemdale.latitude = "22.5330495120487";
        gemdale.houseCode = gson.toJson(HeadJS.getAsJsonObject().get("mainHouseCode")).replace("\"", "");
        gemdale.buildingCode = gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingCode")).replace("\"", "");
        String params222 = DES3Utils.des3Encode(gson.toJson(gemdale), "AOSw9O!^lgquMP$nZDvo%koJ");
        return params222;
    }

    /**
     * 后台测试执行报事请求方法
     *
     * @param jsonResponse 登录且加密后响应的数据
     * @author chenhuayang
     */
    public String testWork(String jsonResponse) throws Exception {
        Gson gson = new Gson();
        GemdaleWork gemdale = new GemdaleWork();
        HashMap head = new HashMap();
        String jsonResponseDecode = messageDecode(jsonResponse);
        String JSON = jsonResponseDecode;
        JsonElement JS = new JsonParser().parse(JSON);
        String HeadJSON = gson.toJson(JS.getAsJsonObject().get("result"));
        JsonElement HeadJS = new JsonParser().parse(HeadJSON);
        gemdale.address = gson.toJson(HeadJS.getAsJsonObject().get("mainProjectName")).replace("\"", "") + gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingName")).replace("\"", "") + gson.toJson(HeadJS.getAsJsonObject().get("mainHouseName")).replace("\"", "");
        gemdale.projectName = gson.toJson(HeadJS.getAsJsonObject().get("projectName")).replace("\"", "");
        gemdale.source = "01";
        gemdale.serviceCode = "ET010205";
        gemdale.appointmentBeginTime = "2018-10-21 16:00:00";
        gemdale.longitude = "113.95476498005301";
        if (head.containsKey("userId")) {
            head.put("sessionId", HeadJS.getAsJsonObject().get("sessionId"));
            head.put("userId", HeadJS.getAsJsonObject().get("userId"));
        } else {
            head.put("sessionId", HeadJS.getAsJsonObject().get("sessionId"));
            head.put("userId", HeadJS.getAsJsonObject().get("userId"));
        }
        long timestamp = System.currentTimeMillis();
        head.put("timestamp", Long.valueOf(timestamp));
        head.put("macId", "");
        head.put("deviceInfo", "PLK-TL01H|6.0[2.9.5]home");
        head.put("token", MD5Utils.MD5Encode(HeadJS.getAsJsonObject().get("sessionId") + HttpUtil.DES_KEY + HeadJS.getAsJsonObject().get("userId") + timestamp));
        gemdale.head = head;
        gemdale.pageSize = 20;
        gemdale.serviceName = "蚊虫消杀";
        gemdale.content = "hfdnhdhdbhdjd";
        gemdale.contactor = gson.toJson(HeadJS.getAsJsonObject().get("userName")).replace("\"", "");
        gemdale.reportUserType = gson.toJson(HeadJS.getAsJsonObject().get("certificateType")).replace("\"", "");
        gemdale.contactMobile = gson.toJson(HeadJS.getAsJsonObject().get("mobile")).replace("\"", "");
        gemdale.reportUserMobile = gson.toJson(HeadJS.getAsJsonObject().get("mobile")).replace("\"", "");
        gemdale.appointmentEndTime = "2018-10-21 17:00:00";
        gemdale.houseName = gson.toJson(HeadJS.getAsJsonObject().get("mainHouseName")).replace("\"", "");
        gemdale.buildingName = gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingName")).replace("\"", "");
        gemdale.projectCode = gson.toJson(HeadJS.getAsJsonObject().get("mainProjectCode")).replace("\"", "");
        gemdale.reportUserName = gson.toJson(HeadJS.getAsJsonObject().get("userName")).replace("\"", "");
        gemdale.latitude = "22.5330495120487";
        gemdale.houseCode = gson.toJson(HeadJS.getAsJsonObject().get("mainHouseCode")).replace("\"", "");
        gemdale.buildingCode = gson.toJson(HeadJS.getAsJsonObject().get("mainBuildingCode")).replace("\"", "");
        System.err.println("++0++" + gson.toJson(gemdale) + "++0++");
        String response22 = onSettingOnlyResponse(gson.toJson(gemdale), "http://10.243.3.18:8888/enjoylink/gkeeper/report/reportAdd.do");
        return response22;
    }
    /**
     * 对数据进行解密
     *
     * @param jsonResponse 欲解密数据
     * @author chenhuayang
     */
    public String messageDecode(String jsonResponse) throws Exception {
        String jsonResponseDecode = DES3Utils.des3Decode(jsonResponse, "AOSw9O!^lgquMP$nZDvo%koJ");
        return jsonResponseDecode;
    }
    /**
     * 对数据进行加密
     *
     * @param jsonResponse 欲加密数据
     * @author chenhuayang
     */
    public String messageEncode(String jsonResponse) throws Exception {
        String jsonResponseEncode = DES3Utils.des3Encode(jsonResponse, "AOSw9O!^lgquMP$nZDvo%koJ");
        return jsonResponseEncode;
    }

    public static void main(String args[]) throws Exception {
        EncodeUtils codeUtils = new EncodeUtils();
        String test = codeUtils.messageEncode(codeUtils.testLogin("18565693063", "a111111"));
        System.out.println(test);
        codeUtils.testWork(test);
        System.err.println("++1++" + codeUtils.testWork(test) + "++1++");
        System.err.println("++2++" + codeUtils.messageDecode("DS2qNHW2mvawQ99IiOom8NG9VWaK8rPYAv02auRyJvElEXGCRkQz59TDqwyT jtFSh_HaC-Qq7LeRlhMvg9hKZ1nECEJIR9ijYLuWyb2lHTpwTicIPpVfFUAx Peb2WjQ7-r9I9TcvyBzxPw04YRTb3AjFVZyhEKWwiryk7YFCv3LJhaPAUSkd gxLgebs1BRa0OEvA7b0jjfJE7JoUyL69EQZzZWEiIrxqpm00yPlO1-MkKRx2 QJAMWSvPVg-btl6YX2fHRjqCa816Y4TLFS96udxVk9sJGnbO67u_qp7fSjmF U-UMdWsqMF5OoXlh560m2qVrECfYDvvhE1vYP2QBGafXln1AtElTFzsSkpu7 i3uI1CZ14psiPTFpHAgGJdNkEhbFFBHKBgtBUD0h9RB3m-xe-txl-9rQ74l7 JSw-JYNkIY2tSGFPDhjoAIj4kZXa72PceqkoB3htFF1JF9J9hNMhwGr8IZb9 iK5KMMfOVSIfpV29a1v21qxdy_navsh2clNuCBd4NrKOMa_QzGMhxIXBNQC6 JPEFroXzxMJCP1pAilJ8vZ_6prSyg65qtEGke69gQb9awskzN_yes1hl_msM CcX5mgdyB3RZeOusaSM6ye1WB-Ys0We6K4Oe2DUEwtBbqmKRIVuAOHuRlM6z JdYrwzfCtGSYOn_U4IXFd-RDnSRJq19nLPpMh5slWA3UbrUNydp5aELKImSn obGrXFFrvMg4NgaIlCenij35qYRdaMh9EIdTJjWxSwrUuunygjHBuuh8T9tN ZdkfdbmLFqGPXLjcsjmRA3Fv212eA-5-W3QDQOUtl5TIFBTbQOsBYhkgNyr_ T8B3Lc1IKHLr4ogwC1OpMjop8-09sg8AS7dNMQVz32ndUvTHa0PYwd2gwKFR XmmGIhnLaLiIcF81a_r3PHHrlwDFk1WJWL5YzGPLeT3AbJuVyaKIP3-UyRdh VtJy_6ceBYDxJMoz3M-jQtuVr2C9HdMMkPup8l4eNREctlEj7jjNlQjdutwL Zd3_") + "++2++");
        System.err.println("++3++" + codeUtils.messageDecode("yR89vR4Fi2EArRDMpC1qsfq8pKkGIf6bMYE1bAqgxHKkfKol-Fdh0AI5RN_Z D-wUczXmqR2u4twIVnNIWj-S6zEuR06jyOmx3sg3RLcQxVpd7UCsf7GNZu9Z pRItTTF-") + "++3++");
        System.err.println("++报事请求信息++" + codeUtils.messageDecode("DS2qNHW2mvawQ99IiOom8NG9VWaK8rPYAv02auRyJvElEXGCRkQz59TDqwyT jtFSh_HaC-Qq7LeRlhMvg9hKZ1nECEJIR9ijYLuWyb2lHTpwTicIPpVfFUAx Peb2WjQ7-r9I9TcvyBzxPw04YRTb3AjFVZyhEKWwiryk7YFCv3LJhaPAUSkd gxLgebs1BRa0OEvA7b0jjfJE7JoUyL69EQZzZWEiIrxqpm00yPlO1-MkKRx2 QJAMWSvPVg-btl6Ye7sb1ZR9uUxoGhzcuAAvXuRF1NExR8ZKiiA31qOZLZi2 0x4kQy9nDY3Nxpuj7TFa03cP3HawSeEbnaYvWMUMFmKtdHPjlElKSY9T3_nS U_SYGMySKMGqhuif3EZK4Rbyi0nXp4wO05TrklM1K-b5RVRHuNj6hq40M5Cr 0dxADa1MOJjsKU5cPn2JLrmE9zdwT3zx0oqoHY_C4dIFBV5fP289FCMdp2OD zhAO7I3X68X88JEnBWgWqUZb848z0T5MN4zoXnBJe7XmVuFq5o-ooCK3pD7j 0acSrFBEoknIe8HmEYLmrvJisc7Qry-uiZik4MsslDdKXOOA8oKW84vvSxce PhC16HkHfXNF2QJd--QeQImPBrncZAJRRLMHMwKxyNxtDW4Bu9zfrHqf38Yl CZBj5X3iiAIyKXVnd5zn8Wzmrd69_7tXjGp4mXZAZJf75N-pJG8oQcn73bGX ICLurUh9GUIIBYgf8c_4piBWDqXxH26-ixnCNHOEbggFpHqIi-Eym6kYfeq0 oBKujpHCMvIJpPhnuyvaGrcvV2SHRTwV_dDDlUe19knDuwNnDp7UY0zwBAYp V_IQizZO91hghVgULOG064My8_0AIfe8gC0835r7S0CuJBpguywjdxcvU_Fy UR83KEVW7c06VV3wduGnMjhGlD7QpGTBjSZrZiNF_YU1pZhYbHRHk-7lTpq2 nORLEXC-xFse3BbCbPHGNHre5nRHCNmjnZYoPUtBwAwRKWk4uZpjdQxiYZ8L skD0xjlQ7qIUXjCIpgtGDjX5YYOwQ3Am-qci_Im4KIKHNrmNK2PdRrkLocZQ dDsmBWwU") + "++4++");
    }
}