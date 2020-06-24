package com.tedu.sois.common.util;

import com.tedu.sois.common.pojo.IPGeolocation;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 通过IP地址获取所在地址
 * @author LYS
 */
public class AddressUtils {

    /**
     *
     * @param content 请求的IP 格式为：111.85.211.126
     * @return
     * @throws UnsupportedEncodingException
     */
    public static IPGeolocation getAddresses(String content){
        //调用 http://ip-api.com/
        String urlStr = "http://ip-api.com/json/";
        String returnStr = getResult(urlStr, content,"utf-8");
        if(returnStr != null){
            JSONObject json = JSONObject.fromObject(returnStr);
            IPGeolocation data = (IPGeolocation)JSONObject.toBean(json, IPGeolocation.class);
            return data;
        }
        IPGeolocation ipGeolocation = new IPGeolocation();
        ipGeolocation.setMessage("未知地址");
        return ipGeolocation;
    }

    /**
     * @param urlStr
     *            请求的地址
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encodingString) {

        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr+content+"?lang=zh-CN");
            // 新建连接实例
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间，单位毫秒
            connection.setConnectTimeout(1000);
            // 设置读取数据超时时间，单位毫秒
            //connection.setReadTimeout(20000);
            //是否打开输出流
            connection.setDoOutput(true);
            //是否打开输入流
            connection.setDoInput(true);
            //提交方法 POST|GET
            connection.setRequestMethod("GET");
            //是否缓存
            connection.setUseCaches(false);
            //打开连接端口
            connection.connect();
            //打开输出流往对端服务器写数据
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //写数据，即提交表单 name=xxx&pwd=xxx
//            out.writeBytes("lang=zh-CN");
            //刷新
            out.flush();
            //关闭输出流
            out.close();
            // 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encodingString));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }
        return null;
    }

}
