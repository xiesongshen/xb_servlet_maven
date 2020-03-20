package com.service;

import com.alibaba.fastjson.JSONObject;
import com.dao.LoginDao;
import com.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @auth admin
 * @date 2020/3/17 16:43
 * @Description
 */
public class LoginService {

    private LoginDao loginDao = new LoginDao();

    public User checkLogin(User user) {
        return loginDao.checkLogin(user);
    }

    /*
     * @description 根据WxOpenid(微信登录标识符) 查询用户
     */
    public User findByWxOpenid(String wxOpenid) {
        return loginDao.findByWxOpenid(wxOpenid);
    }

    /*
     * @description 根据qqOpenid(qq登录标识符) 查询用户
     */
    public User findByQqOpenid(String qqOpenid) {
        return loginDao.findByQqOpenid(qqOpenid);
    }

    /*
     * @description微信调用
     */
    public JSONObject getJsonObjectForWx(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                return JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据url通过HttpClient请求返回QQ的AccessToken
     */
    public String getAccessTokenForQQ(String url) {
        String token = null;
        try {
            // 创建一次HttpClient请求
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                if (result.indexOf("access_token") >= 0) {
                    String[] array = result.split("&");
                    for (String str : array) {
                        if (str.indexOf("access_token") >= 0) {
                            token = str.substring(str.indexOf("=") + 1);
                            break;
                        }
                    }
                }
            }
            httpGet.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public String getQQOpenID(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            int startIndex = result.indexOf("(");
            int endIndex = result.lastIndexOf(")");
            String json = result.substring(startIndex + 1, endIndex);
            jsonObject = JSONObject.parseObject(json);
        }
        httpGet.releaseConnection();
        if (jsonObject != null) {
            return jsonObject.getString("openid");
        } else {
            return null;
        }
    }

    public JSONObject getUserInfoForQQ(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }

}
