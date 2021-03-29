package com.github.oopstool.file;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**
 * 学生测试类
 *
 * @author : HouGY
 * @since : 2021/3/17
 */

/**
 * 行高 列宽
 */
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
public class Student3 {

    @ExcelProperty({"学生信息", "序号"})
    private Integer id;

    @ExcelProperty({"学生信息", "姓名"})
    private String name;

    @ExcelProperty({"学生信息", "年龄"})
    private Integer age;
    @ExcelProperty({"学生信息", "性别"})
    private String sex;
    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty({"学生信息", "出生日期"})
    private String date;
    /**
     * 我想接收百分比的数字
     */
    @NumberFormat("#.##%")
    @ExcelProperty({"学生信息", "绩点"})
    private Double doubleData;

    @ExcelProperty({"地址信息", "省"})
    /**
     * 宽度为50
     */
    @ColumnWidth(50)
    private String address_province;
    @ExcelProperty({"地址信息", "市"})
    private String address_city;
    @ExcelProperty({"地址信息", "区"})
    private String address_area;

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_area() {
        return address_area;
    }

    public void setAddress_area(String address_area) {
        this.address_area = address_area;
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

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }
}
