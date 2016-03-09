/**
 * 
 */
package com.shop.service.enums.mybatis;

/**
 * 枚举工具类
 * 
 * @author tanxianwen 2015年1月27日
 */
public abstract class EnumValueUtil {

    /**
     * 如果枚举类实现了IEnumValue接口，则直接根据value转成相应的枚举对象
     * 
     * @param enumType
     * @param value
     * @return
     * @throws IllegalArgumentException , 如果传入的value在枚举中没有对应值，则抛出异常
     */
    public static <T extends Enum<T> & IEnumValue> T valueOf(Class<T> enumType, int value) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            if (e.getValue() == value) {
                return e;
            }
        }

        throw new IllegalArgumentException("Cannot convert " + value + " to " + enumType.getSimpleName() + " by value.");
    }

    /**
     * 将一个实现了IEnumValue接口的枚举，根据value匹配，转换成指定的目标枚举类型
     * 
     * @param destEnumType
     * @param sourceEnum
     * @return
     */
    public static <T extends Enum<T> & IEnumValue> T castByValue(Class<T> destEnumType, IEnumValue sourceEnum) {
        return valueOf(destEnumType, sourceEnum.getValue());
    }
}
