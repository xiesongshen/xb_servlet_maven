package com.controller;

import com.alibaba.fastjson.JSON;
import com.constants.SysConstant;
import com.entity.Menu;
import com.entity.User;
import com.service.LoginService;
import com.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auth admin
 * @date 2020/3/17 16:34
 * @Description
 */
@WebServlet("/menu/*")
public class MenuServlet extends BaseServlet {

    private MenuService menuService = new MenuService();

    protected void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> list = menuService.listAll();

        //一级菜单
        List<Menu> parent = new ArrayList<>();
        //二级菜单
        List<Menu> son = new ArrayList<>();

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getType() == 0) {
//                parent.add(list.get(i));
//            }
//            if (list.get(i).getType() == 1) {
//                son.add(list.get(i));
//            }
//        }

        parent = list.stream().filter(n -> {
            return n.getType() == 0;
        }).collect(Collectors.toList());

        son = list.stream().filter(n -> {
            return n.getType() == 1;
        }).collect(Collectors.toList());

        Map<String, List<Menu>> map = new HashMap<>();
        map.put("parent", parent);
        map.put("son", son);

        String mapJsonStr = JSON.toJSONString(map);
        PrintWriter out = response.getWriter();
        out.write(mapJsonStr);

    }

}
