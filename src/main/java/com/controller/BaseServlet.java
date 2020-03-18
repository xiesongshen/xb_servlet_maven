package com.controller;

import com.constants.SysConstant;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @auth admin
 * @date 2020/3/18 9:32
 * @Description
 */
public class BaseServlet extends HttpServlet {

    //登录信息
    public User loginUser = new User();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loginUser = (User) session.getAttribute(SysConstant.SESSION_LOGIN_USER);

        //获取uri
        String uri = request.getRequestURI();
        String[] uriArr = uri.split("/");
        uri = uriArr[uriArr.length - 1];

//        LoginServlet loginServlet = new LoginServlet();
//        UserServlet userServlet = new UserServlet();
//        Class clazz = loginServlet.getClass();
        //这里的this不是BaseServlet,谁调用service()就是谁
        Class clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            method.setAccessible(true);
            // /login/login
            // /user/add
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
