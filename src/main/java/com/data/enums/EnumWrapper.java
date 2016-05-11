package com.data.enums;

import com.google.common.base.Preconditions;
import com.data.enums.mybatis.IEnumDesc;
import com.data.enums.mybatis.IEnumValue;

/**
 * 装饰一个枚举为一个bean，以便序列化成对象形式
 *
 * @author tanxianwen 2015年1月13日
 */
public class EnumWrapper {

    private Integer id;
    private String name;
    private Object value;
    private String desc;


    public EnumWrapper(Enum<?> e, boolean useNameAsValue) {
        Preconditions.checkNotNull(e);
        if (e instanceof IEnumValue) {
            id = Integer.valueOf(((IEnumValue) e).getValue());
        } else {
            id = Integer.valueOf(e.ordinal());
        }
        // name = e.name();
        name = e.toString();

        if (useNameAsValue) {
            value = name;
        } else if (e instanceof IEnumValue) {
            value = Integer.valueOf(((IEnumValue) e).getValue());
        } else {
            value = Integer.valueOf(e.ordinal());
        }

        if (e instanceof IEnumDesc) {
            desc = ((IEnumDesc) e).getDesc();
        } else {
            desc = name;
        }
    }

    // public String getName() {
    // return name;
    // }


    public Integer getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "name:" + name + ",value:" + value + ",desc:" + desc;
    }

}
