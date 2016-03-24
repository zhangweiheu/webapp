package com.shop.enums;

import com.shop.enums.mybatis.IEnumDesc;
import com.shop.enums.mybatis.IEnumValue;
import com.shop.enums.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum PassStatusEnum implements IEnumDesc, IEnumValue {
    UNUSE("未使用", 0),
    USED("已使用", 1);

    private String desc;
    private int value;


    PassStatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static PassStatusEnum parse(int val) {
        for (PassStatusEnum cycle : PassStatusEnum.values()) {
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
