package com.github.oopstool.json;

import com.github.oopstool.string.StringUtils;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class JsonUtilsTest {

    private static Student student;

    private static Student student1;

    @Before
    public void before() {
        student = new Student(1, "张三", 23, null);
        student1 = new Student(12, "李四", 24, "女");
    }

    @Test
    public void toJson() {
        ArrayList<Student> arrayList = Lists.newArrayList(student, student1);
        String json = JsonUtils.toJson(student);
        assert !StringUtils.isBlank(json);
        assert arrayList.size()==2;
        System.out.println(json);
        System.out.println(JsonUtils.toJson(arrayList));
    }

    @Test
    public void jsonToBean() {
        String json = "{\"id\":1,\"name\":\"张三\",\"age\":23,\"sex\":\"男\"}";
        Student student = JsonUtils.jsonToBean(json, Student.class);
        assert student.getAge()==23 && student.getId()==1;

        Map<String, String> map = JsonUtils.jsonToBean(json, new TypeToken<Map<String, String>>(){}.getType());
        System.out.println(map);
    }

    @Test
    public void jsonToList() {
        //jsonToList 单个对象
        String studentArray = "[{\"id\":1,\"name\":\"张三\",\"age\":23,\"sex\":null},{\"id\":12,\"name\":\"李四\",\"age\":24,\"sex\":\"女\"}]";
        List<Student> studentList = JsonUtils.jsonToList(studentArray, Student.class);
        assert studentList.size() == 2;
        //jsonToList 复杂对象
        String listMapString="[{\"李四\":24,\"张三\":23},{\"麻子\":26,\"王二\":25}]";
        List<Map<String, Integer>> mapList = JsonUtils.jsonToList(listMapString, new TypeToken<List<Map<String, Integer>>>(){}.getType());
        assert mapList.size()==2;
        System.out.println(JsonUtils.toJson(mapList));
        List<Map> maps = JsonUtils.jsonToList(listMapString, Map.class);
        System.out.println(JsonUtils.toJson(maps));
    }
    @Test
    public  void main1() {
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("张三",23);
        map1.put("李四",24);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("王二",25);
        map2.put("麻子",26);
        ArrayList<HashMap<String, Integer>> hashMaps = Lists.newArrayList(map1, map2);
        System.out.println(JsonUtils.toJson(hashMaps));
    }
}