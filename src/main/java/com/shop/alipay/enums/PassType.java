/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * Alipass类型
 * @author siyu.jsy
 * @version $Id: PassType.java,v 0.1 2013-5-3 下午2:19:18 siyu.jsy Exp $
 */
public enum PassType {

    /**卡券*/
    coupon("coupon"),

    /**票务*/
    eventTicket("eventTicket"),

    /**登车、船、飞机牌*/
    boardingPass("boardingPass"),

    /**会员卡*/
    card("card"),

    /**票务2-新电影票模版*/
    eventTicket2("eventTicket2"),

    /**专享折扣*/
    discount("discount"),

    /**营销券*/
    market("market"),

    /**线下淘宝商家优惠券*/
    mdiscount("mdiscount");

    private String typeName;

    private PassType(String typeName) {
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
    public static PassType get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (PassType keyType : PassType.values()) {
            if (keyType.getTypeName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
