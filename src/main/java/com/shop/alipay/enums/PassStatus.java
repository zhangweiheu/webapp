/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.enums;

/**
 * pass状态枚举
 * 
 * @author junhua.pan
 * @version $Id: PassStatus.java, v 0.1 2014-3-18 下午4:23:03 junhua.pan Exp $
 */
public enum PassStatus {

    /**  pass状态：已关闭*/
    PASS_STATUS_CLOSED("CLOSED"),

    /**  pass状态：已使用*/
    PASS_STATUS_USED("USED");

    /**  状态值*/
    private String code;

    PassStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
