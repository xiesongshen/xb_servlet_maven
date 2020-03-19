package com.service;

import com.dao.MenuDao;
import com.entity.Menu;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/19 9:46
 * @Description
 */
public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
