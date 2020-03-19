package com.dao;

import com.entity.Dept;
import com.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 15:45
 * @Description
 */
public class DeptDao extends BaseDao {

    public List<Dept> listAll() {
        String sql = "select * from sys_dept";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

}
