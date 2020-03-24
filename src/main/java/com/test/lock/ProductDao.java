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

    public int updateStock4(String name, Integer version) {
        String sql = "update product set stock=stock-1,version=version+1 where name= ? and version = ? ";
        return template.update(sql, name, version);
    }

    public Product getByName5(Integer id) {
        //锁行
        String sql = "select * from product where id=? for update";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public int updateStock5(Integer id) {
        String sql = "update product set stock=stock-1,version=version+1 where id= ? and stock > 0 ";
        return template.update(sql, id);
    }

}
