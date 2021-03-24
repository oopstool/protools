package org.oops.protools.core.string;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import org.junit.Test;
import org.oops.protools.core.param.ParamUtils;
import org.oops.protools.core.json.Student;

/**
 * 参数校验表达式
 *
 * @author : HouGY
 * @since : 2021/3/18
 */
public class ParamUtilsTest {

    @Test
    public void checkArgument() {
        //测试单独返回异常
        String a = "a";
        try {
            ParamUtils.checkArgument(StringUtils.isBlank(a));
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
        }
        ParamUtils.checkArgument(a.length() == 1);
        //测试返回异常信息
        ArrayList<Object> list = Lists.newArrayList();
        try {
            ParamUtils.checkArgument(!list.isEmpty(), "集合不能为空");
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
            assert e.getMessage().equals("集合不能为空");
        }
        //测试返回模版异常信息
        Student student = new Student(1, "张三", 23, null);
        try {
            ParamUtils.checkArgument(!StringUtils.isBlank(student.getSex()), "学生(id=%s,姓名=%s)的性别不能为空", student.getId(),
                student.getName());
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
            assert e.getMessage().equals("学生(id=1,姓名=张三)的性别不能为空");
        }
    }

    @Test
    public void testCheckArgument() {
        Preconditions.checkArgument(false,"111",1,"dd");
    }

    @Test
    public void checkNotNull() {
        //校验为null返回NullPointerException
        Student student = null;
        try {
            ParamUtils.checkNotNull(student);
        } catch (Exception e) {
            assert e instanceof NullPointerException;
        }
        //校验为null返回NullPointerException及自定义错误信息
        try {
            ParamUtils.checkNotNull(student,"学生信息不能为空");
        } catch (Exception e) {
            assert e instanceof NullPointerException;
            assert "学生信息不能为空".equals(e.getMessage());
        }
        //校验为null返回NullPointerException及模版错误信息
        try {
            ParamUtils.checkNotNull(student,"%s不能为空","学生信息");
        } catch (Exception e) {
            assert e instanceof NullPointerException;
            assert "学生信息不能为空".equals(e.getMessage());
        }
    }
}
