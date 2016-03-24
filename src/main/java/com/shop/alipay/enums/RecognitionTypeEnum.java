/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;

/**
 * Alipass用户识别方式枚举类型
 * @author dan.lv
 * @version $Id: RecognitionTypeEnum.java,v 0.1 2013-25/10/13 13:54 dan.lv Exp $
 */
public enum RecognitionTypeEnum {

    /**  支付宝交易*/
    TRADE("1", "支付宝交易"),

    /**  支付宝用户ID*/
    @Deprecated
    USERID("2", "支付宝用户ID"),

    /**  支付宝用户绑定手机号*/
    MOBILE("3", "支付宝用户绑定手机号"),

    /**  支付宝公众号开放ID*/
    OPENID("4", "支付宝公众号开放ID"), ;

    /**识别类型*/
    private String code;
    /**描述 */
    private String desc;

    private RecognitionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
