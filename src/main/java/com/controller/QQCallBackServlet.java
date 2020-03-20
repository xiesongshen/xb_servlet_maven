package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.constants.SysConstant;
import com.entity.User;
import com.service.LoginService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @auth admin
 * @date 2020/3/20 10:55
 * @Description
 */
@WebServlet("/qq_login")
public class QQCallBackServlet extends HttpServlet {

    private LoginService loginService = new LoginService();

    private UserService userService = new UserService();

    /*
     * @description 处理QQ回调
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取认证成功后的code
        String code = request.getParameter("code");

        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId = prop.getProperty("qq.AppID");
        String appKey = prop.getProperty("qq.AppKey");
        String redirectUri = prop.getProperty("qq.redirect_uri");

        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + appId +
                "&client_secret=" + appKey +
                "&code=" + code +
                "&redirect_uri=" + redirectUri;

        // 发送HttpClient请求获取access_token(临时票据)
        String access_token = loginService.getAccessTokenForQQ(url);

        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        // 通过access_token获取openid(qq唯一标识符)
        String openid = loginService.getQQOpenID(url);

        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + appId +
                "&openid=" + openid;
        // 通过access_token和openid获取qq的用户信息（昵称，性别，头像...）
        JSONObject jsonObject = loginService.getUserInfoForQQ(url);

        // 根据qqOpenid查询此用户原来有没有使用过qq登录
        User user = loginService.findByQqOpenid(openid);
        // 说明该用户是第一次使用QQ登录
        if (user == null) {
            user = new User();
            // 用户的头像
            user.setPic(jsonObject.getString("figureurl_qq_2"));
            // 用户的昵称
            user.setRealName(jsonObject.getString("nickname"));
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            user.setQqOpenid(openid);
            // 注册一个新的用户
            userService.add(user);

            user = loginService.findByQqOpenid(openid);
        }

        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_LOGIN_USER, user);
        response.sendRedirect("/html/common/home.jsp");
    }

}
