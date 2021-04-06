package com.github.oopstool.json;

import com.github.oopstool.string.StringUtils;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class GsonUtilsTest {

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
        String json = GsonUtils.toJson(student);
        assert !StringUtils.isBlank(json);
        assert arrayList.size() == 2;
        System.out.println(json);
        System.out.println(GsonUtils.toJson(arrayList));
    }

    @Test
    public void jsonToBean() {
        String json = "{\"id\":1,\"name\":\"张三\",\"age\":23,\"sex\":\"男\"}";
        Student student = GsonUtils.jsonToBean(json, Student.class);
        assert student.getAge() == 23 && student.getId() == 1;

        Map map = GsonUtils.jsonToBean(json, new TypeToken<Map<String, String>>() {
        }.getType());
        System.out.println(map);

        String json1 = "{\"id\":第一,\"name\":\"张三\",\"age\":33岁,\"sex\":\"男\"}";
        Map stringObjectMap = GsonUtils.jsonToMap(json1);
        System.out.println(stringObjectMap);

        String json2 = "{\"id\":1,\"name\":\"2\",\"age\":23,\"sex\":\"0\"}";
        Map map2 = GsonUtils.jsonToBean(json2, new TypeToken<Map<String, Integer>>() {
        }.getType());
        System.out.println(map2);

    }

    @Test
    public void jsonToList() {
        //jsonToList 单个对象
        String studentArray = "[{\"id\":1,\"name\":\"张三\",\"age\":23,\"sex\":null},{\"id\":12,\"name\":\"李四\",\"age\":24,\"sex\":\"女\"}]";
        List<Student> studentList = GsonUtils.jsonToList(studentArray);
        assert studentList.size() == 2;
        //jsonToList 复杂对象
        String listMapString = "[{\"李四\":24,\"张三\":23},{\"麻子\":26,\"王二\":25}]";
        List<Map<String, Integer>> mapList = GsonUtils.jsonToList(listMapString, new TypeToken<List<Map<String, Integer>>>() {
        }.getType());
        assert mapList.size() == 2;
        System.out.println(GsonUtils.toJson(mapList));
        List<Map<String, Integer>> maps = GsonUtils.jsonToList(listMapString);
        System.out.println(GsonUtils.toJson(maps));

        List<Map<String, Student>> maps1 = GsonUtils.jsonToListMap(listMapString);

        List<Student> studentList2 = GsonUtils.jsonToList(listMapString, new TypeToken<List<Student>>() {
        }.getType());
        System.out.println(studentList2.size());


    }

    @Test
    public void main1() {
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("张三", 23);
        map1.put("李四", 24);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("王二", 25);
        map2.put("麻子", 26);
        ArrayList<HashMap<String, Integer>> hashMaps = Lists.newArrayList(map1, map2);
        System.out.println(GsonUtils.toJson(hashMaps));
    }
}