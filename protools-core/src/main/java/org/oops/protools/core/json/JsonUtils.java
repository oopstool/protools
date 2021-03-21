package org.oops.protools.core.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 基于Gson的json处理工具
 *
 * @author : HouGY
 * @since : 2021/3/17
 */
public class JsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls().create();
        }
    }


    private JsonUtils() {
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


  /*  public static <T> T gsonToBean(byte[] gsonByte, Class<T> cls) {
        return gsonToBean(new String(gsonByte, "utf-8"), cls);
        return null;
    }*/

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
     * @param cls        转换的类class
     * @param <T>        泛型对象
     * @return 返回指定类对象的集合
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 指定的Json反序列化为指定类对象的list集合<br>
     * 此方法适用于指定的对象是泛型，如果反序列化非通用对象请使用{@link #jsonToList(String, Class)}
     *
     * @param jsonString json字符串
     * @param type       转换的类class
     * @param <T>        泛型对象
     * @return 返回指定类对象的集合
     */
    public static <T> List<T> jsonToList(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

   /* public static <T> List<T> jsonToList(String gsonString, Class<T[]> cls) {
        return Arrays.asList(gson.fromJson(gsonString, cls));
    }*/

    public static <T> List<Map<String, T>> jsonToListMap(String gsonString) {
        List<Map<String, T>> list = null;
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        return list;
    }

    public static <T> Map<String, T> gsonToMap(String gsonString) {
        Map<String, T> map = null;
        try {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String isContain(String a) {

        JsonElement a2 = gson.toJsonTree(a);

        String f = a2.getAsJsonObject().get("error").toString();

        return f;
    }

}
