package com.service;

import com.dao.UserDao;
import com.entity.Page;
import com.entity.User;
import com.utils.DateUtil;
import com.utils.MdUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @auth admin
 * @date 2020/3/17 16:43
 * @Description
 */
public class UserService {

    private UserDao userDao = new UserDao();

    public List<User> listPage(User user, Page page) {
        return userDao.listPage(user, page);
    }

    public Integer count(User user) {
        return userDao.count(user);
    }

    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id, pic);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    //通过id查询用户
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    //根据用户名查询用户
    public User getUserByName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        return userDao.getUserByName(userName);
    }

    public User getUserByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        return userDao.getUserByEmail(email);
    }

    //添加
    public void add(User user) {
        user.setRegisterTime(DateUtil.getDateStr());
        user.setCreateTime(DateUtil.getDateStr());
        user.setCreateBy(null);
        userDao.add(user);
    }

    //修改密码
    public void updatePassword(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(MdUtil.md5(password));
        userDao.updatePassword(user);
    }

    /**
     * 根据部门id查询部门的所属用户
     */
    public List<User> findUserByDeptId(Integer deptId) {
        return userDao.findUserByDeptId(deptId);
    }

}
