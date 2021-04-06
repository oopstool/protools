package com.github.oopstool.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 基于Gson的json处理工具
 *
 * @author : HouGY
 * @since : 1.0.0
 */
public class GsonUtils {

    private static final Gson gson;

    static {
        gson = new GsonBuilder().serializeNulls().create();
    }


    private GsonUtils() {
    }

    /**
     * 将指定的对象序列化为其等效的Json表示形式
     *
     * @param object 需要转换的对象
     * @return 返回json形式字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }


    /**
     * 指定的Json反序列化为指定类的对象
     *
     * @param jsonString json字符串
     * @param cls        转换的类class
     * @return 返回指定类的对象
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, cls);
    }

    /**
     * 指定的Json反序列化为指定类的对象
     *
     * @param jsonString json字符串
     * @param type       转换的类class
     * @return 返回指定类的对象
     */
    public static <T> T jsonToBean(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    /**
     * 指定的Json反序列化为指定类对象的list集合<br>
     * 此方法适用于非通用对象，如果反序列化通用对象请使用{@link #jsonToList(String, Type)}
     *
     * @param jsonString json字符串
     * @param <T>        泛型对象
     * @return 返回指定类对象的集合
     */
    public static <T> List<T> jsonToList(String jsonString) {
        return gson.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 指定的Json反序列化为指定类对象的list集合<br>
     * 此方法适用于指定的对象是泛型，如果反序列化非通用对象请使用{@link #jsonToList(String)}
     * 例如
     * <code>
     * List<Map<String, Integer>> mapList = JsonUtils.jsonToList(listMapString, new TypeToken<List<Map<String, Integer>>>(){}.getType());
     * </code>
     *
     * @param jsonString json字符串
     * @param type       转换的类class
     * @param <T>        泛型对象
     * @return 返回指定类对象的集合
     */
    public static <T> List<T> jsonToList(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    /**
     * 该方法是{@link #jsonToList(String, Type)}方法的常用形式，
     * 如果定制type请使用jsonToList。注意：在没有指定具体的数据类型的时候，会将int类型的数值转换成double。更建议使用jsonToBean。
     *
     * @param jsonString json格式字符串
     * @return 返回转换的list对象
     */
    public static <T> List<Map<String, T>> jsonToListMap(String jsonString) {
        List<Map<String, T>> list;
        list = gson.fromJson(jsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    /**
     * 该方法是{@link #jsonToBean(String, Type)}方法的常用形式，
     * 如果定制type请使用jsonToBean。注意：在没有指定具体的数据类型的时候，会将int类型的数值转换成double。更建议使用jsonToBean。
     *
     * @param jsonString json格式字符串
     * @return 返回map
     */
    public static <T> Map<String, T> jsonToMap(String jsonString) {
        Map<String, T> map;
        map = gson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }


}
