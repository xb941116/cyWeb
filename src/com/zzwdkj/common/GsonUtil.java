package com.zzwdkj.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2015-07-14 14:15
 */
public class GsonUtil {

    private static Gson gson;

    public GsonUtil() {

    }

    public static Gson build() {
        if (gson == null)
            gson = new GsonBuilder().create();
        return gson;
    }

    public static String toJson(Object o) {
        return build().toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return build().fromJson(json, clazz);
    }

    public static <T> List<T> fromJson(String json, Type type) {
        return build().fromJson(json, type);
    }

    public static void main(String[] args) {
        System.out.println(fromJson("",new TypeToken<Map<String,String>>(){}.getType()));
    }
}
