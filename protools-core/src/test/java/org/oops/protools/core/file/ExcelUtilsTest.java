package org.oops.protools.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import junit.framework.TestCase;

public class ExcelUtilsTest extends TestCase {

    public void testRead() throws FileNotFoundException {
        List<Student1> student1List = ExcelUtils.read(new File("/Users/houguangyu/Desktop/测试.xlsx"), Student1.class);
        assert student1List.size()==3;
        List<Student1> student2List = ExcelUtils.read(new FileInputStream(new File("/Users/houguangyu/Desktop/测试.xlsx")), Student1.class);
        assert student2List.size()==3;
    }

    private static class TestExcelListener extends AnalysisEventListener<Student1> {
        private ArrayList<Student1> arrayList = new ArrayList<>();
        @Override
        public void invoke(Student1 student1, AnalysisContext analysisContext) {
            //do something
            arrayList.add(student1);
            System.out.println(student1.getName());
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
    }

    public void testTestRead() {
        ExcelUtils.read(new File("src/test/java/org/oops/protools/core/file/student.xlsx"),
                Student1.class,
                new TestExcelListener());
    }
}