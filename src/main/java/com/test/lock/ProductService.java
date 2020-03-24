package com.test.lock;

import com.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @auth admin
 * @date 2020/3/23 11:03
 * @Description
 */
public class ProductService {

    private ProductDao productDao = new ProductDao();

    public Product getByName(String name) {
        return productDao.getByName(name);
    }

    public int updateStock(String name) {
        return productDao.updateStock(name);
    }

    public int updateStock4(String name, Integer version) {
        return productDao.updateStock4(name, version);
    }

    public Product getByName5(Integer id) {
        return productDao.getByName5(id);
    }

    public int updateStock5(Integer id) {
        return productDao.updateStock5(id);
    }

}
