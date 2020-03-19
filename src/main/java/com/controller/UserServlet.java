package com.controller;

import com.entity.Page;
import com.entity.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 15:07
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    /*
     * @description 查询用户（分页+查询条件）
     * @author admin
     * @date 2020/3/18
     * @param [request, response]
     * @return void
     */
    public void listPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String username = request.getParameter("username");
        username = username == null ? "" : username;

        //当前页
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);

        //构造查询条件对象
        User user = new User();
        user.setUsername(username);

        //总记录数
        Integer count = userService.count(user);

        Page page = new Page();
        page.setPageCurrent(pageCurrent);
        page.setCount(count);

        //数据
        List<User> list = userService.listPage(user, page);
        request.setAttribute("list", list);
        request.setAttribute("user", user);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);
    }

    /*
     * @description 注册用户
     * @author admin
     * @date 2020/3/18
     * @param [request, response]
     * @return void
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);

        userService.add(user);
        //注册成功，跳转到登录页面
        response.sendRedirect("/index.jsp");

    }

    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        User result = userService.getUserByName(name);
        PrintWriter out = response.getWriter();
        if (result == null) {
            out.write("200");
        } else {
            out.write("500");
        }
    }

    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User result = userService.getUserByEmail(email);
        PrintWriter out = response.getWriter();
        if (result == null) {
            out.write("200");
        } else {
            out.write("500");
        }
    }

}
