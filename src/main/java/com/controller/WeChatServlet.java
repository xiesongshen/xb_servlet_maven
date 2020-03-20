package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.constants.SysConstant;
import com.entity.User;
import com.service.LoginService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.UUID;

/**
 * @auth admin
 * @date 2020/3/20 10:55
 * @Description
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends BaseServlet {

    private LoginService loginService = new LoginService();

    private UserService userService = new UserService();

    /*
     * @description 点击微信登录，跳转到微信授权页面,获取code
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void wxLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载配置文件
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        //微信应用AppID
        String appId = prop.getProperty("wx.AppID");
        //微信授权成功后的回调地址
        String redirectUri = prop.getProperty("wx.redirect_uri");

        //固定格式
        //Step1：获取Authorization Code
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri) +
                "&scope=snsapi_login";

        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }

    /*
     * @description 处理微信回调
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void wxLoginCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载配置文件
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId = prop.getProperty("wx.AppID");
        String appSecret = prop.getProperty("wx.AppSecret");

        //获取微信授权后返回的code
        String code = request.getParameter("code");
        //固定格式
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        // 通过code获取access_token、openid等数据
        JSONObject info = loginService.getJsonObject(url);
        System.out.println("info: " + info);

        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + info.getString("access_token") +
                "&openid=" + info.getString("openid");
        //通过access_token和openid获取微信的用户信息（昵称，性别，头像...）
        JSONObject userInfo = loginService.getJsonObject(url);
        System.out.println("userInfo: " + userInfo);

        // 根据微信的openid查询此用户原来有没有使用过微信登录
        User user = loginService.findByWxOpenid(info.getString("openid"));

        // 如果该用户是第一次使用微信登录,把从微信返回的用户信息保存的数据库
        if (user == null) {
            user = new User();
            // 用户的头像
            user.setPic(userInfo.getString("headimgurl"));
            // 性别
            user.setSex(Integer.valueOf(userInfo.getString("sex")));
            // 用户的昵称
            user.setRealName(userInfo.getString("nickname"));
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            user.setWxOpenid(info.getString("openid"));
            // 注册一个新的用户
            userService.add(user);
            user = loginService.findByWxOpenid(info.getString("openid"));
        }

        HttpSession session = request.getSession();
        // 修改登录时间
        // userService.updateLoginTime(user.getId());
        session.setAttribute(SysConstant.SESSION_LOGIN_USER, user);
        response.sendRedirect("/html/common/home.jsp");

    }
}
