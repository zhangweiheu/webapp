/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * pass子类型枚举
 * @author siyu.jsy
 * @version $Id: ProductType.java,v 0.1 2013-5-3 下午2:36:55 siyu.jsy Exp $
 */
public enum ProductType {

    /**免费券*/
    free("free"),

    /**兑换券*/
    price("price"),

    /**团购券*/
    groupon("groupon"),

    /**彩票*/
    lottery("lottery"),

    /**电影票*/
    movie("movie"),

    /**入场券*/
    ticket("ticket"),

    /**旅游景点门票*/
    tourist("tourist"),

    /**酒店*/
    hotel("hotel"),

    /**登机牌*/
    boarding("boarding"),

    /**机票*/
    air("air"),

    /**火车票*/
    train("train"),

    /** 汽车票*/
    bus("bus"),

    /** 会员卡*/
    memberCard("memberCard"),

    /**资金卡*/
    mFundCard("mFundCard"),

    /**专享折扣*/
    discount("discount"),

    /**无申领专享折扣*/
    nonApplyDiscount("nonApplyDiscount"),

    /**营销券*/
    market("market"),

    /**线下淘宝商家优惠券*/
    mdiscount("mdiscount");

    private String typeName;

    private ProductType(String typeName) {
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
    public static ProductType get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (ProductType keyType : ProductType.values()) {
            if (keyType.getTypeName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
