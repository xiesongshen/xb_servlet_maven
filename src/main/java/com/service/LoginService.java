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
     * @author admin
     * @date 2020/3/19
     * @param [wxOpenid]
     * @return com.entity.User
     */
    public User findByWxOpenid(String wxOpenid) {
        return loginDao.findByWxOpenid(wxOpenid);
    }

    public JSONObject getJsonObject(String url) {
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

}
