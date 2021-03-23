package org.oops.protools.core.file;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于easyexcel工具类
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
     * @param cls  夺取转换对象cls
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
     * @param cls         夺取转换对象cls
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
     * </p>
     *
     * @param file 要读取的文件
     * @param cls 要转换的class
     * @param readListener 监听器
     */
    public static void read(File file, Class cls, ReadListener readListener) {
        EasyExcelFactory.read(file, cls, readListener).sheet().doRead();
    }

    public static void read(InputStream inputStream, Class cls, ReadListener readListener) {
        EasyExcelFactory.read(inputStream, cls, readListener).sheet().doRead();
    }

}
