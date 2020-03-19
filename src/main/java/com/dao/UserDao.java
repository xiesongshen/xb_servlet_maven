package com.dao;

import com.entity.Page;
import com.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Date;
import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 15:45
 * @Description
 */
public class UserDao extends BaseDao {

    public List<User> listPage(User user, Page page) {
        String sql = "SELECT " +
                "u.id id, " +
                "u.dept_id deptId, " +
                "u.username username, " +
                "u.email email, " +
                "u.real_name realName, " +
                "u.age age, " +
                "u.phone phone, " +
                "u.sex sex, " +
                "u.is_secret isSecret, " +
                "u.create_time createTime, " +
                "su.username createName  " +
                "FROM " +
                "sys_user u " +
                "left join sys_user su on su.id=u.create_by " +
                "where u.username like ? " +
                "limit ? , ? ";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class),
                "%" + user.getUsername() + "%",
                (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer count(User user) {
        String sql = "SELECT " +
                "count(*) " +
                "from " +
                "sys_user " +
                "where " +
                "username like ? ";
        return template.queryForObject(sql, Integer.class, "%" + user.getUsername() + "%");
    }

    public void updateUser(User user) {
        String sql = "update sys_user set password=?,real_name=?,age=?,phone=?,sex=?,description=?,is_secret=?,dept_id=?,login_time=? where id=?";
        template.update(sql,
                user.getPassword(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getSex(),
                user.getDescription(),
                user.getIsSecret(),
                user.getDeptId(),
                user.getLoginTime(),
                user.getId()
        );
    }

    public User getUserById(Integer id) {
        String sql = "select * from sys_user where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updatePic(Integer id, String pic) {
        String sql = "update sys_user set pic=? where id=?";
        template.update(sql, pic, id);
    }

    public User getUserByName(String userName) {
        try {
            String sql = "select * from sys_user where username=?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            String sql = "select * from sys_user where email=?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void add(User user) {
        String sql = "insert into sys_user(id,dept_id, username, password, email, qq_openid, wx_openid, real_name, age, phone, sex, description , register_time, login_time, pic, look, is_secret, create_time, create_by) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getDeptId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getSex(),
                user.getDescription(),
                user.getRegisterTime(),
                user.getLoginTime(),
                user.getPic(),
                user.getLook(),
                user.getIsSecret(),
                user.getCreateTime(),
                user.getCreateBy()
        );
    }

}
