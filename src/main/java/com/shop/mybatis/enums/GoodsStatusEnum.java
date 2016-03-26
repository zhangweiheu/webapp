package com.shop.mybatis.enums;

import com.shop.mybatis.IEnumDesc;
import com.shop.mybatis.IEnumValue;
import com.shop.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum GoodsStatusEnum implements IEnumDesc, IEnumValue {
    NORMAL("正常", 0),
    REMOVE_SUPPORT("已撤架", 1),
    RECOMMEND("推荐", 2);

    private String desc;
    private int value;


    GoodsStatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static GoodsStatusEnum parse(int val) {
        for (GoodsStatusEnum cycle : GoodsStatusEnum.values()) {
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
