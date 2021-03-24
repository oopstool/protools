package org.oops.protools.core.file;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 基于EasyExcel工具类
 *
 * @author : HouGY
 * @since : 2021/3/23
 */
public class ExcelUtils {
    private static class DefaultExcelListener<T> extends AnalysisEventListener<T> {
        private List<T> data = new ArrayList<>();

        @Override
        public void invoke(T t, AnalysisContext analysisContext) {
            data.add(t);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        }

        public List<T> getData() {
            return data;
        }

    }

    /**
     * <p>
     * 读取excel返回指定的对象集合
     * 此方法只读取第一个sheet页。从第一行数据开始读取
     * 例：
     *      <ul>
     *          {@code ExcelUtils.read(new File("测试.xlsx"), Student1.class); returns List<Student1>}
     *      </ul>
     * </p>
     *
     * @param file 要读取的excel
     * @param cls  读取转换对象cls
     * @param <T>  泛型
     * @return 返回指定的对象集合
     */
    public static <T> List<T> read(File file, Class<T> cls) {
        DefaultExcelListener defaultExcelListener = new DefaultExcelListener();
        EasyExcelFactory.read(file, cls, defaultExcelListener).sheet().doRead();
        return defaultExcelListener.getData();
    }

    /**
     * <p>
     * 读取指定的excel sheet页 返回指定的对象集合
     * 此方法从第一行数据开始读取
     * 例：
     *      <ul>
     *          {@code ExcelUtils.read(new File("测试.xlsx"), Student1.class,1); returns List<Student1>}
     *      </ul>
     * </p>
     *
     * @param file 要读取的excel
     * @param cls  读取转换对象cls
     * @param sheet 需要读取sheet页码，默认为0，起始值为0
     * @param <T>  泛型
     * @return 返回指定的对象集合
     */
    public static <T> List<T> read(File file, Class<T> cls,int sheet) {
        DefaultExcelListener defaultExcelListener = new DefaultExcelListener();
        EasyExcelFactory.read(file, cls, defaultExcelListener).sheet(sheet).doRead();
        return defaultExcelListener.getData();
    }

    /**
     * <p>
     * 根据指定的sheet页从指定的行开始读 返回指定的对象集合
     * 注意此方法要转成的对象的属性 应该按照表头属性书写，或者给属性设置 @ExcelProperty(index = 3) index。
     * 如果index 和name 混用会产生不能赋值的问题
     * 例：
     *      <ul>
     *          {@code ExcelUtils.read(new File("测试.xlsx"), Student1.class,1); returns List<Student1>}
     *      </ul>
     * </p>
     *
     * @param file 要读取的excel
     * @param cls  读取转换对象cls
     * @param sheet 需要读取sheet页码，默认为0，起始值为0
     * @param headRowNumber 行头的行数 例如headRowNumber=2则读取数据从第3行还是读取
     * @param <T>  泛型
     * @return 返回指定的对象集合
     */
    public static <T> List<T> read(File file, Class<T> cls,int sheet,int headRowNumber) {
        DefaultExcelListener defaultExcelListener = new DefaultExcelListener();
        EasyExcelFactory.read(file, cls, defaultExcelListener).sheet(sheet).headRowNumber(headRowNumber).doRead();
        return defaultExcelListener.getData();
    }

    /**
     * <p>
     * 读取excel 所有的sheet页 返回指定的对象集合
     * 此方法从第一行数据开始读取
     * 例：
     *      <ul>
     *          {@code ExcelUtils.read(new File("测试.xlsx"), Student1.class,1); returns List<Student1>}
     *      </ul>
     * </p>
     *
     * @param file 要读取的excel
     * @param cls  读取转换对象cls
     * @param <T>  泛型
     * @return 返回指定的对象集合
     */
    public static <T> List<T> readAllSheet(File file, Class<T> cls) {
        DefaultExcelListener defaultExcelListener = new DefaultExcelListener();
        EasyExcelFactory.read(file, cls, defaultExcelListener).doReadAll();
        return defaultExcelListener.getData();
    }

    /**
     * <p>
     * 读取excel返回指定的对象集合
     * 此方法只读取第一个sheet页。从第一行数据开始读取
     * 该方法主要针对web端上传使用 MultipartFile file
     * 例：
     *      <ul>
     *          {@code ExcelUtils.read(file.getInputStream(), Student1.class); returns List<Student1>}
     *      </ul>
     * </p>
     *
     * @param inputStream 文件流
     * @param cls         读取转换对象cls
     * @param <T>         泛型
     * @return 返回指定的对象集合
     */
    public static <T> List<T> read(InputStream inputStream, Class<T> cls) {
        DefaultExcelListener defaultExcelListener = new DefaultExcelListener();
        EasyExcelFactory.read(inputStream, cls, defaultExcelListener).sheet().doRead();
        return defaultExcelListener.getData();
    }

    /**
     * <p>
     *     根据自定义监听器读取excel
     *     此方法只读取第一个sheet页。从第一行数据开始读取
     * </p>
     *
     * @param file 要读取的文件
     * @param cls 要转换的class
     * @param readListener 监听器
     */
    public static void read(File file, Class cls, ReadListener readListener) {
        EasyExcelFactory.read(file, cls, readListener).sheet().doRead();
    }

    /**
     *<p>
     *     默认写入指定的文件的第一个sheet页
     *</p>
     * @param file 要写入的文件
     * @param cls  写入指定的class
     * @param t 要写入的数据
     */
    public static <T> void write(File file, Class<T> cls,List<T> t) {
        EasyExcelFactory.write(file, cls).sheet("Sheet1").doWrite(t);
    }

    /**
     *<p>
     *     根据文件路径默认写入指定的文件的第一个sheet页
     *</p>
     * @param path 文件路径
     * @param cls  写入指定的class
     * @param t 要写入的数据
     */
    public static <T> void write(String path, Class<T> cls,List<T> t) {
        EasyExcelFactory.write(path, cls).sheet("Sheet1").doWrite(t);
    }

    /**
     *<p>
     *     默认写入指定的文件的第一个sheet页忽略掉指定的列
     *</p>
     * @param file 文件
     * @param cls  写入指定的class
     * @param excludeColumnSet  忽略的字段
     * @param t 要写入的数据
     */
    public static <T> void writeByExcludeColumn(File file, Class<T> cls,List<T> t, Set<String> excludeColumnSet) {
        EasyExcelFactory.write(file, cls).excludeColumnFiledNames(excludeColumnSet).sheet("Sheet1").doWrite(t);
    }

    /**
     *<p>
     *     默认写入指定的文件的第一个sheet页,只写入指定的列
     *</p>
     * @param file 文件
     * @param cls  写入指定的class
     * @param includeColumnSet  指定的字段集合
     * @param t 要写入的数据
     */
    public static <T> void writeByIncludeColumn(File file, Class<T> cls,List<T> t, Set<String> includeColumnSet) {
        EasyExcelFactory.write(file, cls).includeColumnFiledNames(includeColumnSet).sheet("Sheet1").doWrite(t);
    }

    /**
     *<p>
     *    指定sheet页写如指定的列。如果不指定则写入全部的列
     *</p>
     * @param file 文件
     * @param cls  写入指定的class
     * @param sheetName sheet名称
     * @param includeColumnSet  指定的字段集合
     * @param t 要写入的数据
     */
    public static <T> void write(File file, Class<T> cls,List<T> t,String sheetName ,Set<String> includeColumnSet) {
        if(includeColumnSet==null || includeColumnSet.isEmpty()){
            EasyExcelFactory.write(file, cls).sheet(sheetName).doWrite(t);
        }else {
            EasyExcelFactory.write(file, cls).includeColumnFiledNames(includeColumnSet).sheet(sheetName).doWrite(t);
        }

    }

    /**
     * <p>
     *     通过输入流写入该方法适合web端下载
     *     例：
     *        <ul>
     *            {@code
     *          public void download(HttpServletResponse response) throws IOException {
     *         response.setContentType("application/vnd.ms-excel");
     *         response.setCharacterEncoding("utf-8");
     *         // 这里URLEncoder.encode可以防止中文乱码
     *         String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
     *         response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
     *         ExcelUtils.write(response.getOutputStream(), DownloadData.class,data);
     *     }
     *                }
     *        </ul>
     * </p>
     * @param outputStream 输入流
     * @param cls clazz
     * @param t 泛型对象
     */
    public static <T> void write(OutputStream outputStream, Class<T> cls,List<T> t) {
        EasyExcelFactory.write(outputStream,cls).sheet("Sheet1").doWrite(t);
    }


}
