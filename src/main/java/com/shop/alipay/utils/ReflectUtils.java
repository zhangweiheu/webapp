package com.shop.alipay.utils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 反射工具类</br>
 * <p>获取source中指定key的valuse值</p>
 * 
 * @author hengchang.yu
 * @version $Id: ReflectUtils.java, v 0.1 2013年11月6日 下午1:08:36 hengchang.yu Exp $
 */
public final class ReflectUtils {

    @SuppressWarnings("rawtypes")
    public static Object invoke(Object source, String key) {
        if (source instanceof Map) {
            return valueOfMap((Map) source, key);
        } else {
            return valueOfObj(source, key);
        }
    }

    @SuppressWarnings("rawtypes")
    private static Object valueOfMap(Map map, String key) {
        if (map.isEmpty() || key == null || key.trim() == "") {
            return null;
        }
        return map.get(key);
    }

    private static Object valueOfObj(Object o, String property) {
        if (o == null) {
            return null;
        }
        if (property == null || "".equals(property.trim())) {
            return o;
        }
        String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);

        Object result = null;
        try {
            Method method = o.getClass().getMethod(methodName);
            result = method.invoke(o, new Object[] {});
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

}
