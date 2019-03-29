package com.pan.lib.util;


import com.alibaba.fastjson.JSON;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanKit {
    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     */
    public static <T> T string2Bean(String json, Class<T> beanClass) {
        T object = JSON.parseObject(json, beanClass);
        if (object == null) {
            try {
                return (T) beanClass.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;// 默认为UTF-8
    }

    /**
     * 将bean转换为map
     * ---------------------
     * //blog.csdn.net/Pan_cras/article/details/52172000
     */
    public static <T> Map<String, String> bean2Map(T bean) {
//        Method[] methods = bean.getClass().getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.print(method.getName() + "() ");
//        }
//        System.out.println();
//        Method method = bean.getClass().getDeclaredMethod("setCover3", String.class);
//        method.invoke(bean, "haha");
//
//        Class<?> clazz = Class.forName("bean.Bean");
//        Constructor<?> constructor = clazz.getConstructor();
//        System.out.println(constructor.getName());
//
//        System.out.println(bean.getCover3());

        Map<String, String> resultMap = new HashMap<>();
        Map entryMap = new HashMap<>();
        try {
            entryMap = BeanUtils.describe(bean);
            BeanUtils.copyProperties(entryMap, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (Object object : entryMap.entrySet()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) object;
            resultMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        return resultMap;

        //TODO
    }

}
