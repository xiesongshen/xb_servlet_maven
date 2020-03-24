package com.test.jdbctest;

/**
 * @author wang
 * @version 2.0
 * @company
 * @date 2019/9/10 16:58
 * @decription
 */
public class User {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    public User() {
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
