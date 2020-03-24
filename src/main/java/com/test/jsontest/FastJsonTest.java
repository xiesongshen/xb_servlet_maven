package com.test.jsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description fastjson(阿里巴巴)
 */
public class FastJsonTest {

    public static void main(String[] args) throws Exception {
        // fastjson(阿里巴巴)
        //1 对象转json字符串 万能的方法： JSON.toJSONString();
        //2 json字符串转对象  万能的方法： JSON.parseObject(jsonStr,new TypeReference<泛型>(){});

        // jackson(springmvc)
        //1 对象转json字符串 万能的方法： om.writeValueAsString();
        //2 json字符串转对象 万能的方法： om.readValue(jsonStr,new TypeReference<泛型>(){});

        // 区别：
        // System.out.println("fastjson:" + JSON.toJSONString(user));
        // System.out.println("jackson:" + om.writeValueAsString(user));

        User user = new User("admin", 20);
        User user2 = new User("admin2", 18);

        List<String> listStr = new ArrayList<>();
        listStr.add("hello");
        listStr.add("world");

        List<User> listUser = new ArrayList<>();
        listUser.add(user);
        listUser.add(user2);

        Map<String, User> mapUser = new HashMap<>();
        mapUser.put("class1", user);
        mapUser.put("class2", user2);

        // 测试一：javaBean和json字符串
        String userJsonStr = JSON.toJSONString(user);
        User user11 = JSON.parseObject(userJsonStr, User.class);
        User user12 = JSON.parseObject(userJsonStr, new TypeReference<User>() {
        });

        //测试二：List<String>和json字符串
        String listJsonStr = JSON.toJSONString(listStr);
        List<String> listStr11 = JSON.parseArray(listJsonStr, String.class);
        List<String> listStr12 = JSON.parseObject(listJsonStr, new TypeReference<List<String>>() {
        });
        listStr12.stream().forEach(System.out::println);

        //测试三：List<User>和json字符串
        String listUserJsonStr = JSON.toJSONString(listUser);
        List<User> listUser11 = JSON.parseArray(listUserJsonStr, User.class);
        List<User> listUser12 = JSON.parseObject(listUserJsonStr, new TypeReference<List<User>>() {
        });
        listUser12.stream().forEach(n -> {
            System.out.println(n.getName());
        });

        //测试四：Map<String, User>和json字符串
        String mapUserJsonStr = JSON.toJSONString(mapUser);
        Map<String, User> mapUser11 = JSON.parseObject(mapUserJsonStr, new TypeReference<Map<String, User>>() {
        });

    }

}
