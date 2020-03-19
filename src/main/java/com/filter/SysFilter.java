package com.filter;

import com.constants.SysConstant;
import com.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        //不需要拦截的，直接放行
        if (
                uri.endsWith("/index.jsp") ||
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
