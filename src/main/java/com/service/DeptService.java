package com.service;

import com.dao.DeptDao;
import com.entity.Dept;
import com.entity.Page;
import com.utils.DateUtil;

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

    public List<Dept> listPage(Dept dept, Page page) {
        return deptDao.listPage(dept, page);
    }

    public Integer count(Dept dept) {
        return deptDao.count(dept);
    }

    /*
     * @description 通过id删除
     * @author admin
     * @date 2020/3/19
     * @param [id]
     * @return void
     */
    public void deleteById(Integer id) {
        deptDao.deleteById(id);
    }

    /*
     * @description 添加部门
     * @author admin
     * @date 2020/3/19
     * @param [dept]
     * @return void
     */
    public void addDept(Dept dept) {
        dept.setCreateTime(DateUtil.getDateStr());
        deptDao.addDept(dept);
    }

    /*
     * @description 通过id查询部门
     * @author admin
     * @date 2020/3/19
     * @param [id]
     * @return com.entity.Dept
     */
    public Dept getDeptById(Integer id) {
        return deptDao.getDeptById(id);
    }

    /*
     * @description 修改部门
     * @author admin
     * @date 2020/3/19
     * @param [dept]
     * @return void
     */
    public void updateDept(Dept dept) {
        deptDao.updateDept(dept);
    }

}
