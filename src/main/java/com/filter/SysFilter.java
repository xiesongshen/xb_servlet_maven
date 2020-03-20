package com.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.constants.SysConstant;
import com.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @auth admin
 * @date 2020/3/17 16:36
 * @Description
 */
@WebFilter("/*")
public class SysFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        //拦截到登录请求 http://localhost:8080/index.jsp
        if (uri.endsWith("/index.jsp")) {
            //获取所有的cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    //如果cookie中有登录信息，则直接跳转到home.jsp
                    if (SysConstant.COOKIE_LOGIN_USER.equals(cookies[i].getName())) {
                        //取出cookie中的值，并解码，得到的是json字符串
                        String strJson = URLDecoder.decode(cookies[i].getValue(), "utf-8");
                        User loginUser = JSON.parseObject(strJson, new TypeReference<User>() {
                        });
                        //把从cookie中取出的loginUser放入session中
                        session.setAttribute(SysConstant.SESSION_LOGIN_USER, loginUser);
                        //免登录，直接跳转到home.jsp
                        request.getRequestDispatcher("/html/common/home.jsp").forward(request, response);
                    }
                }
            }

            //如果没有cookie，则直接放行（去登录界面）
            filterChain.doFilter(request, response);
            return;

        } else if (
            //不需要拦截的，直接放行
                uri.endsWith("register.jsp") ||
                        uri.endsWith("register") ||
                        uri.endsWith("login") ||
                        uri.endsWith("checkUserName") ||
                        uri.endsWith("checkEmail") ||
                        uri.endsWith("getPic") ||
                        uri.endsWith("wxLogin") ||
                        uri.endsWith("wxLoginCallBack")
        ) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_USER);
        if (obj == null) {
            //session中没有登录信息（非法登录）
            response.sendRedirect("/index.jsp");
            return;
        }

        request.setAttribute("loginUser", (User) obj);
        //放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
