package com.lyj.gzh.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.util.Map;

@Slf4j
public class HttpUtil {

    public static String get(String url) throws Exception {
        log.info(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        log.info("请求返回：" + response);
        return String.valueOf(response);

    }

    public static String post(String url, Map<String, Object> params) throws Exception {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0; )
            sb.append((char) c);
        String response = sb.toString();
        log.info(response);
        return response;
    }

    /**
     * 发送ajax请求
     * @param url
     * @param param
     * @return
     */
    public static String postJson(String url,Map<String,Object> param){
//        OutputStreamWriter out =null;
//        BufferedReader reader = null;
//        String response = "";
//
//        //创建连接
//        try {
//            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
//            //创建URL
//            httpUrl = new URL(url);
//            //建立连接
//            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("connection", "keep-alive");
//            conn.setUseCaches(false);//设置不要缓存
//            conn.setInstanceFollowRedirects(true);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.connect();
//            //POST请求
//            out = new OutputStreamWriter(
//                    conn.getOutputStream());
//
//            out.write(param);
//            out.flush();
//            //读取响应
//            reader = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream()));
//            String lines;
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), "utf-8");
//                response+=lines;
//            }
//            reader.close();
//            // 断开连接
//            conn.disconnect();
//
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(reader!=null){
//                    reader.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//
//        return response;
        return null;
    }
}
