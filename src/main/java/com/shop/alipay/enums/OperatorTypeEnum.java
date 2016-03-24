/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;

/**
 * 支付宝核销时的操作类型
 * @author dan.lv
 * @version $Id: OperatorTypeEnum.java,v 0.1 2013-25/10/13 13:54 dan.lv Exp $
 */
public enum OperatorTypeEnum {

    /**操作员手工核销*/
    MANUAL("1", "手工核销"),
    /**终端机具核销*/
    TERMINAL("2", "机具核销"), ;
    /**操作类型*/
    private String code;
    /**描述 */
    private String desc;

    private OperatorTypeEnum(String code, String desc) {
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
