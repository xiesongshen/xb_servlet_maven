package com.service;

import com.dao.UserDao;
import com.entity.Page;
import com.entity.User;
import com.utils.DateUtil;
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

    /*
     * @description 通过id查询用户
     * @author admin
     * @param [id]
     * @return com.entity.User
     */
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    /*
     * @description 根据用户名查询用户
     * @param [userName]
     * @return com.entity.User
     */
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

    /*
     * @description 添加
     * @param [user]
     * @return void
     */
    public void add(User user) {
        user.setRegisterTime(DateUtil.getDateStr());
        user.setCreateTime(DateUtil.getDateStr());
        user.setCreateBy(null);
        userDao.add(user);
    }

}
