package com.test.jdbctest.test2;

import com.test.jdbctest.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @version 2.0
 * @company
 * @date 2019/9/11 16:04
 * @decription
 */
public class UserDao2 {

    public static void main(String[] args) {

    }

    public static void save(User user) {
        String sql = "insert into user (id ,name,password) values(null,?,?)";
        DbUtil2.dml(sql, user.getName(), user.getPassword());
    }

    public static List<User> select() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = DbUtil2.getConn();
            String sql = "select * from user";
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String ps = rs.getString("password");
                user.setId(id);
                user.setName(name);
                user.setPassword(ps);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil2.close(rs, state, conn);
        }
        return list;
    }

}
