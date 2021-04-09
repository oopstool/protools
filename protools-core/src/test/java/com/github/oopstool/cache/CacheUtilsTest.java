package com.github.oopstool.cache;

import com.github.oopstool.json.Student;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class CacheUtilsTest {

    @Test
    public void buildCache() throws Exception {
        CacheLoader<String, List<String>> cacheLoader = new CacheLoader<String, List<String>>() {
            @Override
            public List<String> load(String key) {
                return loadCache(key);
            }
        };
        LoadingCache<String,List<String>> loadingCache = CacheUtils.buildCache(100,10, 1, 32,100, cacheLoader);
        System.out.println(loadingCache.get("测试"));
        Thread.sleep(6000);
        System.out.println("====++++"+loadingCache.getIfPresent("测试"));
        System.out.println("===="+loadingCache.get("测试"));
    }


    @Test
    public void buildCache1() throws Exception {

        Function<String,String> function = new Function<String,String>() {
            @Nullable
            @Override
            public String apply(@Nullable String string) {
                return string+"A";
            }
        };

        CacheLoader<String, String> from = CacheUtils.from(function);


        LoadingCache<String, String> stringStringLoadingCache = CacheUtils.buildCache(2, 1, 1, 32, 100, from);

        System.out.println(stringStringLoadingCache.getIfPresent("1"));
        assert stringStringLoadingCache.getIfPresent("1")==null;
        System.out.println(stringStringLoadingCache.get("1"));
        System.out.println(stringStringLoadingCache.getIfPresent("1"));
        assert Objects.equals(stringStringLoadingCache.getIfPresent("1"), "1A");
        Thread.sleep(2000);
        assert stringStringLoadingCache.getIfPresent("1")==null;

        System.out.println(stringStringLoadingCache.get("1"));
        System.out.println(stringStringLoadingCache.getIfPresent("1"));
        assert Objects.equals(stringStringLoadingCache.getIfPresent("1"), "1A");
    }

    @Test
    public void buildCache2() throws Exception {
        Supplier<Student> supplier = new Supplier<Student>() {
            @Override
            public Student get() {
                Student student = new Student();
                student.setId(1);
                student.setName("张三");
                return student;
            }
        };
        CacheLoader<Object, Student> from = CacheUtils.from(supplier);
        from.load(new Student());

        LoadingCache<Object, Student> objectStudentLoadingCache = CacheUtils.buildCache(20, 1, 2, 8, 12, from);

        System.out.println(objectStudentLoadingCache.get("1").getName());


    }


    public static List<String> loadCache(String key){
        System.out.println("loadCache begin"+key+System.currentTimeMillis());
        List<String> list = Lists.newArrayList("AAA"+key, "BBB"+key);
        System.out.println("loadCache end");
        return list;
    }

    public static String loadCache1(String key){
        return key+"A";
    }

    @Test
    public void buildCacheWithoutExpire() throws ExecutionException, InterruptedException {
        Function<String,String> function = new Function<String,String>() {
            @Nullable
            @Override
            public String apply(@Nullable String string) {
                return string+"A";
            }
        };

        CacheLoader<String, String> from = CacheUtils.from(function);


        LoadingCache<String, String> stringStringLoadingCache = CacheUtils.buildCacheWithoutExpire(1, 1, 32, 100, from);
        stringStringLoadingCache.get("1");
        Thread.sleep(4000);
        assert Objects.equals(stringStringLoadingCache.getIfPresent("1"), "1A");


    }
}