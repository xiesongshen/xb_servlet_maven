package com.controller;

import com.alibaba.fastjson.JSON;
import com.constants.SysConstant;
import com.entity.Dept;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DeptService;
import com.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

}
