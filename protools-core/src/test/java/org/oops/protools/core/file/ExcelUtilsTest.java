package org.oops.protools.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import junit.framework.TestCase;

public class ExcelUtilsTest extends TestCase {

    public void testRead() throws FileNotFoundException {
        List<Student1> student1List = ExcelUtils.read(new File("/Users/houguangyu/Desktop/测试.xlsx"), Student1.class);
        assert student1List.size()==3;
        List<Student1> student2List = ExcelUtils.read(new FileInputStream(new File("/Users/houguangyu/Desktop/测试.xlsx")), Student1.class);
        assert student2List.size()==3;
    }
}