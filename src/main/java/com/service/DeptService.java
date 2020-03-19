package com.service;

import com.dao.DeptDao;
import com.entity.Dept;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/19 16:22
 * @Description
 */
public class DeptService {

    private DeptDao deptDao = new DeptDao();

    /*
     * @description 查询所有部门
     * @author admin
     * @date 2020/3/19
     * @param []
     * @return java.util.List<com.entity.Dept>
     */
    public List<Dept> listAll() {
        return deptDao.listAll();
    }
}
