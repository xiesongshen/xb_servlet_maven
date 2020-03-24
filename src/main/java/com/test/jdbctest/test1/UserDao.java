package com.test.jdbctest.test1;

import com.dfbz.sys.jdbctest.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @version 2.0
 * @decription
 */
public class UserDao {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

    }

    public static List<User> select() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            //注册数据库驱动
            Class.forName(DRIVER);
            //获取连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from user";
            //实例化PreparedStatement对象
            stat = conn.prepareStatement(sql);
            //获取返回结果集
            rs = stat.executeQuery();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源 先开后关，后开先关
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return list;
    }

    public static void save(User user) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    public static void delete(User user) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "delete from user where id=?";
            stat = conn.prepareStatement(sql);
            stat.setObject(1, user.getId());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
        }

    }

    public static void update(User user) {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "update user set name=? ,password=? where id=?";
            stat = conn.prepareStatement(sql);
            stat.setObject(1, user.getName());
            stat.setObject(2, user.getPassword());
            stat.setObject(3, user.getId());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
        }

    }
}
