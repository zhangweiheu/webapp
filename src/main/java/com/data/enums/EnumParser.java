/**
 * 
 */
package com.data.enums;



import com.data.enums.mybatis.ParseNameReplaceValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析一个枚举类成一个对象列表
 * 
 * @author tanxianwen 2015年1月13日
 */
public class EnumParser {

    private static ConcurrentHashMap<Class<Enum<?>>, List<EnumWrapper>> cache = new ConcurrentHashMap<Class<Enum<?>>, List<EnumWrapper>>();

    /**
     * 解析一个枚举类中的所有成员
     * 
     * @param enumClass
     * @return
     */
    public static List<EnumWrapper> getEnumList(Class<? extends Enum<?>> enumClass) {
        List<EnumWrapper> list = cache.get(enumClass);
        if (null == list) {
            boolean replace = enumClass.isAnnotationPresent(ParseNameReplaceValue.class);
            Enum<?>[] enums = enumClass.getEnumConstants();
            list = new ArrayList<EnumWrapper>(enums.length);
            for (Enum<?> e : enums) {
                list.add(new EnumWrapper(e, replace));
            }
        }

        return list;
    }

    public static List<EnumWrapper> getEnumListForBackend(Class<? extends Enum<?>> enumClass) {
        List<EnumWrapper> list = cache.get(enumClass);
        if (null == list) {
            Enum<?>[] enums = enumClass.getEnumConstants();
            list = new ArrayList<EnumWrapper>(enums.length);
            for (Enum<?> e : enums) {
                list.add(new EnumWrapper(e, false));
            }
        }

        return list;
    }

}
