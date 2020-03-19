package com.dao;

import com.entity.Menu;
import com.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 15:45
 * @Description
 */
public class MenuDao extends BaseDao {

    public List<Menu> listAll() {
        String sql = "select * from sys_menu order by order_by";
        return template.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}