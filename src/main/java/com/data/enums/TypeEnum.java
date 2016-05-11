package com.data.enums;

import com.data.enums.mybatis.IEnumDesc;
import com.data.enums.mybatis.IEnumValue;
import com.data.enums.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum TypeEnum implements IEnumDesc, IEnumValue {
    NEWS("新闻", 0),
    ARTICLE("文章", 1),
    BMFW("便民服务", 2);

    private String desc;
    private int value;

    TypeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static TypeEnum parse(int val) {
        for (TypeEnum cycle : TypeEnum.values()) {
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
