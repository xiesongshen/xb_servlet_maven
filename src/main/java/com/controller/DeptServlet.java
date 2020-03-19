package com.controller;

import com.alibaba.fastjson.JSON;
import com.constants.SysConstant;
import com.entity.Dept;
import com.entity.Page;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DeptService;
import com.service.LoginService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {

    private DeptService deptService = new DeptService();

    private ObjectMapper objectMapper = new ObjectMapper();

    /*
     * @description 查询所有部门
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> list = deptService.listAll();
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(list));
        //out.write(objectMapper.writeValueAsString(list));
    }

    /*
     * @description 查询部门（分页+查询条件）
     * @author admin
     * @date 2020/3/18
     * @param [request, response]
     * @return void
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String name = request.getParameter("name");
        name = name == null ? "" : name;

        //当前页
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);

        //构造查询条件对象
        Dept dept = new Dept();
        dept.setName(name);

        //总记录数
        Integer count = deptService.count(dept);

        Page page = new Page();
        page.setPageCurrent(pageCurrent);
        page.setCount(count);

        //数据
        List<Dept> list = deptService.listPage(dept, page);
        request.setAttribute("list", list);
        request.setAttribute("dept", dept);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/html/dept/list.jsp").forward(request, response);
    }

    /*
     * @description 通过id删除
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return;
        }
        deptService.deleteById(Integer.valueOf(id));
        response.sendRedirect("/dept/list");
    }

    /*
     * @description 添加部门
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void addDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        Map<String, String[]> map = request.getParameterMap();
        Dept dept = new Dept();
        BeanUtils.populate(dept, map);
        dept.setCreateBy(loginUser.getId());
        deptService.addDept(dept);
        response.sendRedirect("/dept/list");
    }

    /*
     * @description 通过id查询部门
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void toUpdateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return;
        }
        Dept dept = deptService.getDeptById(Integer.valueOf(id));
        request.setAttribute("dept", dept);
        request.getRequestDispatcher("/html/dept/update.jsp").forward(request, response);
    }

    /*
     * @description 修改部门
     * @author admin
     * @date 2020/3/19
     * @param [request, response]
     * @return void
     */
    public void updateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        Map<String, String[]> map = request.getParameterMap();
        Dept dept = new Dept();
        BeanUtils.populate(dept, map);
        deptService.updateDept(dept);
        response.sendRedirect("/dept/list");
    }

}
