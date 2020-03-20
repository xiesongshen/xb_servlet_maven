package com.controller;

import com.alibaba.fastjson.JSON;
import com.constants.SysConstant;
import com.entity.User;
import com.service.LoginService;
import com.utils.MdUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private LoginService loginService = new LoginService();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            String picCode = request.getParameter("picCode");
            String remember = request.getParameter("remember");

            HttpSession session = request.getSession();
            Object picObj = session.getAttribute(SysConstant.SESSION_PIC_CODE);
//            if (picCode == null) {
//                response.sendRedirect("/index.jsp");
//                return;
//            }
//
//            if (!picObj.toString().equals(picCode)) {
//                System.out.println("验证码不正确！");
//                response.sendRedirect("/index.jsp");
//                return;
//            }

            User user = new User();
            user.setUsername(name);
            user.setPassword(MdUtil.md5(password));

            User loginUser = loginService.checkLogin(user);
            if (loginUser == null) {
                //重新登录
                response.sendRedirect("/index.jsp");
                return;
            } else {
                //把登录用户放入session中
                session.setAttribute(SysConstant.SESSION_LOGIN_USER, loginUser);
                //过期时间（单位是秒）
                //session.setMaxInactiveInterval(60*30);

                //勾选了记住我
                if (StringUtils.isNotEmpty(remember) && "1".equals(remember)) {
                    //cookie的值不能存特殊字符（， :）
                    String strJson = JSON.toJSONString(loginUser);
                    //先编码，取值的时候再解码
                    strJson = URLEncoder.encode(strJson, "utf-8");
                    Cookie cookieLoginUser = new Cookie(SysConstant.COOKIE_LOGIN_USER, strJson);
                    //7天有效期（单位是秒）
                    cookieLoginUser.setMaxAge(7 * 24 * 60 * 60);
                    //任何请求都能获取cookie
                    cookieLoginUser.setPath("/");
                    response.addCookie(cookieLoginUser);
                }

                request.setAttribute("loginUser", loginUser);
                //登录成功，跳转到主界面
                request.getRequestDispatcher("/html/common/home.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //重新登录
            response.sendRedirect("/index.jsp");
            return;
        }
    }

    /*
     * @description 登出
     * @author admin
     * @date 2020/3/20
     * @param [request, response]
     * @return void
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清除session中的loginUser
        request.getSession().removeAttribute(SysConstant.SESSION_LOGIN_USER);
        //清除cookie中的loginUser
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (SysConstant.COOKIE_LOGIN_USER.equals(cookies[i].getName())) {
                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }
        }

        response.sendRedirect("/index.jsp");
    }

}
