package org.oops.protools.core.file;

import com.alibaba.excel.exception.ExcelDataConvertException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import junit.framework.TestCase;
import org.oops.protools.core.date.DateUtils;
import org.oops.protools.core.security.RandomUtils;
import org.oops.protools.core.string.StringUtils;

public class ExcelUtilsTest extends TestCase {

    public void testRead() throws FileNotFoundException {
        File file = new File("src/test/java/org/oops/protools/core/file/测试.xlsx");
        List<Student1> student1List = ExcelUtils.read(file, Student1.class);
        assert student1List.size()==3;
        List<Student1> student2List = ExcelUtils.read(new FileInputStream(file), Student1.class);
        assert student2List.size()==3;
        List<Student1> student3List = ExcelUtils.read(file, Student1.class,1);
        assert student3List.size()==2;
        List<Student1> student4List = ExcelUtils.readAllSheet(file, Student1.class);
        assert student4List.size()==5;

        List<Student1> student5List = ExcelUtils.read(file, Student1.class,0,3);
        assert student5List.size()==1;
    }

    public void testWrite() throws FileNotFoundException {
        File file = new File("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + ".csv");
        ExcelUtils.write(file,Student2.class,getData());
        ExcelUtils.write("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + ".xlsx",Student2.class,getData());
        ExcelUtils.write(file,Student2.class,getData(),"测试",Sets.newHashSet("name", "sex"));
        File file3 = new File("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + ".xlsx");
        ExcelUtils.write(file3,Student3.class,getData3(),"测试",null);
        File file4 = new File("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + "输入流.xlsx");
        ExcelUtils.write(new FileOutputStream(file4), Student3.class, getData3());
    }

    private List<Student3> getData3() {
        ArrayList<Student3> objects = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Student3 student3 = new Student3();
            student3.setId(i);
            student3.setName("张三"+i);
            student3.setAge(20+i);
            student3.setSex(i%2==0?"女":"男");
            student3.setDate(DateUtils.getToday());
            student3.setDoubleData(RandomUtils.randomDouble(1,99));
            objects.add(student3);
        }
        return objects;
    }

    private List<Student2> getData() {
        ArrayList<Student2> objects = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Student2 student2 = new Student2();
            student2.setId(i);
            student2.setName("张三"+i);
            student2.setAge(20+i);
            student2.setSex(i%2==0?"女":"男");
            student2.setDate(DateUtils.getToday());
            student2.setDoubleData(String.valueOf(RandomUtils.randomDouble(1,99)));
            objects.add(student2);
        }
       return objects;
    }

    public void testWriteByExcludeColumn() {
        File file = new File("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + ".xlsx");
        HashSet<String> strings = Sets.newHashSet("age", "doubleData");
        ExcelUtils.writeByExcludeColumn(file,Student2.class,getData(),strings);
    }

    public void testWriteByIncludeColumn() {
        File file = new File("src/test/java/org/oops/protools/core/file/" + System.currentTimeMillis() + ".xlsx");
        HashSet<String> strings = Sets.newHashSet("name", "age");
        ExcelUtils.writeByIncludeColumn(file,Student2.class,getData(),strings);
    }


    private static class TestExcelListener extends AnalysisEventListener<Student1> {
        private ArrayList<Student1> arrayList = new ArrayList<>();
        @Override
        public void invoke(Student1 student1, AnalysisContext analysisContext) {
            //do something 判断的是否为空等
            if(student1.getAge()==null){
                throw new IllegalArgumentException("年龄不能为空");
            }
            arrayList.add(student1);
            System.out.println( StringUtils.append("姓名:",student1.getName()));
            System.out.println(StringUtils.format("姓名:%s,年龄:%s",student1.getName(),student1.getAge()));
            // 可以做保存数据库的操作
            if(arrayList.size()>1000){
                System.out.println("批量保存");
                arrayList.clear();
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            //do something after all
            System.out.println(arrayList.size());
        }
        /**
         * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
         *
         * @param exception
         * @param context
         * @throws Exception
         */
        @Override
        public void onException(Exception exception, AnalysisContext context) {
            System.out.println(StringUtils.append("解析失败，但是继续解析下一行:",exception.getMessage()));
            // 如果是某一个单元格的转换异常 能获取到具体行号
            // 如果要获取头的信息 配合invokeHeadMap使用
            if (exception instanceof ExcelDataConvertException) {
                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
                System.out.println(StringUtils.format("第%s行，第%s列解析异常",excelDataConvertException.getRowIndex(),excelDataConvertException.getColumnIndex()));
            }
            if (exception instanceof IllegalArgumentException) {
                IllegalArgumentException illegalArgumentException = (IllegalArgumentException)exception;
                System.out.println(StringUtils.append("excel格式不正确",illegalArgumentException.getMessage()));
                System.out.println(StringUtils.format("第%s行,excel格式不正确",context.readRowHolder().getRowIndex(),illegalArgumentException.getMessage()));
            }
        }
    }

    public void testTestRead() {
        ExcelUtils.read(new File("src/test/java/org/oops/protools/core/file/student.xlsx"),
                Student1.class,
                new TestExcelListener());
    }
}