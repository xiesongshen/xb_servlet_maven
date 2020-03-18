package com.dao;

import com.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 15:45
 * @Description
 */
public class LoginDao extends BaseDao {

    public User checkLogin(User user) {
        String sql = "select * from sys_user where username=? and password =?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
    }

}
