package com.dao;

import com.entity.Dept;
import com.entity.Page;
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

    public List<Dept> listPage(Dept dept, Page page) {
        String sql = "select " +
                "d.id id, " +
                "d.name name, " +
                "d.create_time createTime, " +
                "su.username createName, " +
                "ifnull( a.num, 0 ) num  " +
                "from " +
                "sys_dept d " +
                "LEFT JOIN ( SELECT dept_id deptId, count(*) num FROM sys_user su WHERE 1 = 1 GROUP BY su.dept_id ) a ON a.deptId = d.id " +
                "LEFT JOIN sys_user su ON su.id = d.create_by  " +
                "where " +
                "d.name like ?  " +
                "ORDER BY " +
                "d.create_time desc  " +
                "limit ? , ? ";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class),
                "%" + dept.getName() + "%",
                (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer count(Dept dept) {
        String sql = "SELECT " +
                "count(*) " +
                "from " +
                "sys_dept " +
                "where " +
                "name like ? ";
        return template.queryForObject(sql, Integer.class, "%" + dept.getName() + "%");
    }

    public void deleteById(Integer id) {
        String sql = "delete from sys_dept where id=?";
        template.update(sql, id);
    }

    public void addDept(Dept dept) {
        String sql = "insert into sys_dept(id, name, create_time, create_by) values (null,?,?,?)";
        template.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy());
    }

    public Dept getDeptById(Integer id) {
        String sql = "select * from sys_dept where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), id);
    }

    public void updateDept(Dept dept) {
        String sql = "update sys_dept set name = ?  where id=?";
        template.update(sql, dept.getName(), dept.getId());
    }

}
