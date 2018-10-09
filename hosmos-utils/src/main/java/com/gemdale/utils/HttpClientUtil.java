package com.gemdale.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    public static String sendJsonRequest(String url, String jsonRequest, String infoArea, boolean isResDecode, EncodeUtils test) throws Exception {
        /*
         * print sendvalue;
         */
        System.out.println("--url--" + url);
        System.out.println("--jsonRequest--" + jsonRequest);
        System.out.println("--infoArea--" + infoArea);
        System.out.println("--isResDecode--" + isResDecode);


        String jsonResponse = null;
        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout",
                Integer.valueOf(10000));
        httpClient.getParams().setParameter("http.socket.timeout",
                Integer.valueOf(10000));
        HttpPost postMethod = new HttpPost(url);

        StringEntity entity = new StringEntity(jsonRequest, "UTF-8");
        entity.setContentEncoding(new BasicHeader("Content-Encoding",
                Consts.UTF_8.toString()));
        entity.setContentType(new BasicHeader("Content-Type",
                "application/json;charset=UTF-8"));

        postMethod.setEntity(entity);
        //postMethod.getParams().setParameter("http.useragent",FileUtils.getProperty("user-agent"));
        postMethod.getParams().setParameter("http.useragent", "enjoy_link|ghome|3.0.0");

        HttpResponse response = httpClient.execute(postMethod);
        System.out.println("--response--");
        System.out.println("--response--" + response);
        if (200 == response.getStatusLine().getStatusCode()) {

            HttpEntity httpEntity = response.getEntity();
            jsonResponse = EntityUtils.toString(httpEntity);
            System.out.println("--response -- jsonResponse--" + jsonResponse);
            EntityUtils.consume(response.getEntity());
        }
        System.out.println("--------444444--------" + jsonResponse);
        if ((jsonResponse == null) || (jsonResponse.trim().length() < 1)) {
            //test.showDialog("返回数据为空" + jsonResponse);
            System.out.println("返回数据为空" + jsonResponse);
        } else {
            //infoArea.append(isResDecode ? CodingUtil.des3Decode(jsonResponse,
            //		FileUtils.getProperty("3des_key")) : jsonResponse);

            jsonResponse = isResDecode ? DES3Utils.des3Decode(jsonResponse, "AOSw9O!^lgquMP$nZDvo%koJ") : jsonResponse;
            //test.setResponse11(jsonResponse);
            System.out.println("----------8888888--------" + jsonResponse);
        }
        return jsonResponse;
    }
}
