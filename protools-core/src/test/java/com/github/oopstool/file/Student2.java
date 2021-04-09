package com.github.oopstool.file;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;

/**
 * 学生测试类
 *
 * @author : HouGY
 * @since : 2021/3/17
 */
public class Student2 {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "序号", index = 1)
    private Integer id;
    @ExcelProperty(value = "年龄", index = 3)
    @ExcelIgnore
    private Integer age;
    @ExcelProperty(value = "性别", index = 2)
    private String sex;
    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "出生日期", index = 4)
    private String date;
    /**
     * 我想接收百分比的数字
     */
    @NumberFormat("#.##%")
    @ExcelProperty(value = "绩效", index = 5)
    private String doubleData;

    @ExcelIgnore
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(String doubleData) {
        this.doubleData = doubleData;
    }
}
