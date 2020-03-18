package com.dao;

import com.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth admin
 * @date 2020/3/17 15:46
 * @Description
 */
public class BaseDao {

    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

}
