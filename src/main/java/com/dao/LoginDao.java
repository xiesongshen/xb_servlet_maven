package com.dao;

import com.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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


    public User findByWxOpenid(String wxOpenid) {
        String sql = "select * from sys_user where wx_openid=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), wxOpenid);
        } catch (EmptyResultDataAccessException exception) {
            // 如果是没有查询到则返回null
            return null;
        }
    }

}
