package com.test.jsontest;

/**
 * @author wang
 * @version 2.0
 * @company
 * @date 2019/9/10 16:58
 * @decription
 */
public class User {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
