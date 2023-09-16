package com.proj.web.util;


import org.apache.poi.ss.formula.functions.T;
import sun.tools.jar.Main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ReflectionExample {
    public static void main(String[] args) {

    }


    /**
     * 通过反射获取未知类的属性信息
     * @param obj
     * @throws IllegalAccessException
     */
    public static List<String> printFields(Object obj) {
        Class<?> objClass = obj.getClass();
        List<String> list = new ArrayList<>();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);
            list.add(field.getName());
        }
        return list;
    }

    /**
     * 通过反射获取未知类的属性值
     * @param obj
     * @throws IllegalAccessException
     */
    public static List<String> printValues(Object obj) {
        Class<?> aClass = obj.getClass();
        List<String> list = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        Object[] objects = new Object[fields.length][2];
        for (int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            try {
                System.out.println(String.valueOf(fields[i].get(obj)));
                list.add(String.valueOf(fields[i].get(obj)));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    /**
     * 通过反射创建新的field对象进行添加
     * @param obj 创建出来的实体
     * @param key 需要添加的属性
     * @param value 需要添加的字段名中的数据
     * @return
     */
    public static void setFields(Object obj, String key, Object value) {
        try {
            Class<?> aClass = obj.getClass();
            Field field = aClass.getDeclaredField(key);
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
