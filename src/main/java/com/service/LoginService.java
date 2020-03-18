package com.service;

import com.dao.LoginDao;
import com.entity.User;

/**
 * @auth admin
 * @date 2020/3/17 16:43
 * @Description
 */
public class LoginService {

    private LoginDao loginDao = new LoginDao();

    public User checkLogin(User user) {
        return loginDao.checkLogin(user);
    }

}
