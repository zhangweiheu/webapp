/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

public enum EInfoBizFieldEnums {

    /**卡券*/
    from("from"),

    /**票务*/
    to("to"),

    /**机牌*/
    fltNo("fltNo"),
    
    /**bus*/
    busNo("busNo"),
    
    /**train*/
    trainNo("trainNo");

    private String typeName;

    private EInfoBizFieldEnums(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static EInfoBizFieldEnums get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (EInfoBizFieldEnums keyType : EInfoBizFieldEnums.values()) {
            if (keyType.getTypeName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }
}
