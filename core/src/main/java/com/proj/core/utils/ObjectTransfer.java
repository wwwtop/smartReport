package com.proj.core.utils;

import cn.hutool.core.bean.BeanUtil;
import org.assertj.core.util.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

/**
 * 对象赋值和转换辅助工具类
 *
 * @author dong.ning
 */
public class ObjectTransfer {

    /**
     * 对象赋值转换，list专用
     *
     * @param sourceObject
     * @param targetObjectClass
     * @param <SourceObject>
     * @param <TargetObject>
     * @return
     */
    public static <SourceObject, TargetObject> List<TargetObject> copyPropertiesExtraList(
            SourceObject sourceObject, Class<TargetObject> targetObjectClass) {
        if (null == sourceObject) {
            return null;
        }

        List<TargetObject> list = Lists.newArrayList();
        if (sourceObject instanceof List) {
            for (SourceObject source : (List<SourceObject>) sourceObject) {
                TargetObject target = ObjectTransfer.copyPropertiesExtra(source, targetObjectClass);
                list.add(target);
            }
            return list;
        }

        list.add(ObjectTransfer.copyPropertiesExtra(sourceObject, targetObjectClass));
        return list;
    }

    /**
     * 对象赋值转换。适用于单个实体对象、单个实体对象里面嵌入list。的互转
     *
     * @param sourceObject
     * @param targetObjectClass
     * @param <SourceObject>
     * @param <TargetObject>
     * @return
     */
    public static <SourceObject, TargetObject> TargetObject copyPropertiesExtra(
            SourceObject sourceObject, Class<TargetObject> targetObjectClass) {
        if (null == sourceObject) {
            return null;
        }

        try {
            TargetObject targetObject = targetObjectClass.newInstance();
            BeanUtil.copyProperties(sourceObject, targetObject);
            Field[] fields = targetObjectClass.getDeclaredFields();
            for (Field field : fields) {
                Class cls = field.getType();
                String subClassName = cls.getName();
                String fieldName = field.getName();

                if (subClassName.contains("BO") || subClassName.contains("PO")) {
                    String bindFieldName = fieldName;
                    if (fieldName.contains("BO") || fieldName.contains("PO")) {
                        bindFieldName = fieldName.contains("BO") ? fieldName.replace("BO", "PO") : fieldName.replace("PO", "BO");
                    }

                    Object value = invokeGet(sourceObject, bindFieldName);
                    if (null != value) {
                        Object target = cls.newInstance();
                        BeanUtil.copyProperties(value, target);
                        invokeSet(targetObject, fieldName, target);
                    }
                } else if (subClassName.contains("LIST")) {
                    ParameterizedType pt = (ParameterizedType) field.getGenericType();
                    Class clz = (Class) pt.getActualTypeArguments()[0];
                    if (clz.getName().contains("BO") || clz.getName().contains("PO")) {
                        String bindFieldName = fieldName;
                        if (fieldName.contains("BO") || fieldName.contains("PO")) {
                            bindFieldName = fieldName.contains("BO") ? fieldName.replace("BO", "PO") : fieldName.replace("PO", "BO");
                        }

                        Object value = invokeGet(sourceObject, bindFieldName);
                        if (null != value) {
                            List<Object> targetList = new LinkedList<Object>();
                            for (Object obj : (List<Object>) value) {
                                Object target = clz.newInstance();
                                BeanUtil.copyProperties(obj, target);
                                targetList.add(target);
                            }
                            invokeSet(targetObject, fieldName, targetList);
                        }
                    }
                }
            }
            return targetObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 执行set方法
     *
     * @param o
     * @param fieldName
     * @param value
     */
    private static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);

        try {
            method.invoke(o, value);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 执行get方法
     *
     * @param o
     * @param fieldName
     * @return
     */
    private static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);

        try {
            return method.invoke(o);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取get方法定义
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    private static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1));

        System.out.println(stringBuilder.toString());

        try {
            return objectClass.getMethod(stringBuilder.toString());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取set方法定义
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    private static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("set");
            stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
            stringBuilder.append(fieldName.substring(1));

            System.out.println(stringBuilder.toString());

            return objectClass.getMethod(stringBuilder.toString(), parameterTypes);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

}
