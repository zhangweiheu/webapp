package com.shop.service.enums;

import com.shop.service.enums.mybatis.IEnumDesc;
import com.shop.service.enums.mybatis.IEnumValue;
import com.shop.service.enums.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum TagEnum implements IEnumDesc, IEnumValue {
    JAVA("Java", 0),
    HTML("html", 1),
    SYSTEM("操作系统", 2),
    NETWORK("计算机网络", 3);

    private String desc;
    private int value;


    TagEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static TagEnum parse(int val) {
        for (TagEnum cycle : TagEnum.values()) {
            if (cycle.value == val) {
                return cycle;
            }
        }
        return null;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
