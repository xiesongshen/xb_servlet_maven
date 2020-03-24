package com.test.jdbctest.test4;

import com.dfbz.sys.jdbctest.User;
import com.dfbz.sys.jdbctest.test1.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @auth wangzy
 * @date 2020/2/19 18:38
 * @Description
 */
public class UserDao4 {

    public static void main(String[] args) {
        int count = 100;

        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = new User(null, "admin" + i, "123");
            UserDao.save(user);
        }
        Long end1 = System.currentTimeMillis();
        System.out.println("不使用连接池的时间：" + (end1 - start1));

        Long start2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            User user = new User(null, "admin" + i, "456");
            save(user);
        }
        Long end2 = System.currentTimeMillis();
        System.out.println("使用连接池的时间：" + (end2 - start2));

    }

    public static void save(User user) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            //c3p0
            conn = DbUtil4.getConn();
            String sql = "insert into user (id,name,password) values(null,?,?)";
            stat = conn.prepareStatement(sql);
            stat.setObject(1, user.getName());
            stat.setObject(2, user.getPassword());
            int reslut = stat.executeUpdate();
            if (reslut > 0) {
//                System.out.println("添加成功！");
            } else {
//                System.out.println("添加失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
