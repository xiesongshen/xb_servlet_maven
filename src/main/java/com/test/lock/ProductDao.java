package com.test.lock;

import com.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @auth admin
 * @date 2020/3/23 11:03
 * @Description
 */
public class ProductDao extends BaseDao {

    public Product getByName(String name) {
        String sql = "select * from product where name=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), name);
    }

    public int updateStock(String name) {
        String sql = "update product set stock=stock-1 where name= ?";
        return template.update(sql, name);
    }

}
