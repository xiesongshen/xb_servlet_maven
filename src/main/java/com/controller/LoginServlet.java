package com.controller;

import com.constants.SysConstant;
import com.entity.User;
import com.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            user.setPassword(password);

            User result = loginService.checkLogin(user);
            if (result == null) {
                //重新登录
                response.sendRedirect("/index.jsp");
                return;
            } else {
                //把登录用户放入session中
                session.setAttribute(SysConstant.SESSION_LOGIN_USER, result);

                //过期时间（单位是秒）
                //session.setMaxInactiveInterval(60*30);

                request.setAttribute("loginUser", result);
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

}
