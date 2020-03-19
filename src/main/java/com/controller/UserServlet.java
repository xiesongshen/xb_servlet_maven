package com.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.entity.Page;
import com.entity.User;
import com.service.DeptService;
import com.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @auth admin
 * @date 2020/3/17 15:07
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();
    private DeptService deptService = new DeptService();

    /*
     * @description 查询用户（分页+查询条件）
     * @author admin
     * @date 2020/3/18
     * @param [request, response]
     * @return void
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /*
     * @description 查看用户详情
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        request.setAttribute("user", user);
        request.setAttribute("deptList", deptService.listAll());
        request.getRequestDispatcher("/html/user/detail.jsp").forward(request, response);
    }

    /*
     * @description 修改用户信息
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        //org.apache.commons.beanutils.BeanUtils;
        BeanUtils.populate(user, map);

        User user2 = userService.getUserById(user.getId());
        //复制旧的属性过来，忽略null属性，有值的以新的为主，null的则以旧为主
        //cn.hutool.core.bean.BeanUtil;
        BeanUtil.copyProperties(user, user2,
                true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        userService.updateUser(user2);
        response.sendRedirect("/user/listPage");
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
