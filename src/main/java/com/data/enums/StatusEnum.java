package com.data.enums;

import com.data.enums.mybatis.IEnumDesc;
import com.data.enums.mybatis.IEnumValue;
import com.data.enums.mybatis.ParseNameReplaceValue;

@ParseNameReplaceValue
public enum StatusEnum implements IEnumDesc, IEnumValue {
    AUDIT("待审核", 0),
    ACCEPT("审核通过", 1),
    REFUSE("审核不通过", 2),
    BLACKLIST("黑名单",3),
    NORMAL("活动进行中",4),
    OVER("活动结束",5);

    private String desc;
    private int value;


    StatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static StatusEnum parse(int val) {
        for (StatusEnum cycle : StatusEnum.values()) {
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
