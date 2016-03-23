package com.shop.enums;

import com.shop.enums.mybatis.IEnumDesc;
import com.shop.enums.mybatis.IEnumValue;
import com.shop.enums.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum ExpressStatusEnum implements IEnumDesc, IEnumValue {
    BEIN_GPROCESSED("处理中", 0),
    DELIVERED("已发货", 1),
    IN_TRANSIT("送货中", 2),
    RECEIVE("送货中", 3),
    RECEIVED("已签收", 4);

    private String desc;
    private int value;


    ExpressStatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static ExpressStatusEnum parse(int val) {
        for (ExpressStatusEnum cycle : ExpressStatusEnum.values()) {
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
