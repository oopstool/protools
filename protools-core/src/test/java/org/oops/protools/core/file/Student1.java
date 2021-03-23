package org.oops.protools.core.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;

/**
 * 学生测试类
 *
 * @author : HouGY
 * @since : 2021/3/17
 */
public class Student1 {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty(index = 0)
    private Integer id;
    @ExcelProperty(index = 2)
    private Integer age;
    @ExcelProperty(index = 3)
    private String sex;
    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String date;
    /**
     * 我想接收百分比的数字
     */
    @NumberFormat("#.##%")
    private String doubleData;



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
