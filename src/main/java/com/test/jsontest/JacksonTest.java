package com.test.jsontest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description jackson(springmvc)
 */
public class JacksonTest {

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
        ObjectMapper om = new ObjectMapper();

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
        String omUserJsonStr = om.writeValueAsString(user);
        User user13 = om.readValue(omUserJsonStr, User.class);
        User user14 = om.readValue(omUserJsonStr, new TypeReference<User>() {
        });

        //测试二：List<String>和json字符串
        String omListJsonStr = om.writeValueAsString(listStr);
        List<String> listStr13 = om.readValue(omListJsonStr, new TypeReference<List<String>>() {
        });

        //测试三：List<User>和json字符串
        String omListUserJsonStr = om.writeValueAsString(listUser);
        List<User> listUser13 = om.readValue(omListUserJsonStr, new TypeReference<List<User>>() {
        });

        //测试四：Map<String, User>和json字符串
        String omMapUserJsonStr = om.writeValueAsString(mapUser);
        Map<String, User> mapUser12 = om.readValue(omMapUserJsonStr, new TypeReference<Map<String, User>>() {
        });

    }

}
