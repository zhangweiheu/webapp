/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 当为EventTicket类型卡券，且操作信息类型为text时需要用到本model类
 * @author siyu.jsy
 * @version $Id: TextMessageModel.java,v 0.1 2013-5-30 下午2:27:20 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class TextMessageModel extends BaseModel {

    private static final long serialVersionUID = 2386715983646922108L;

    private String            label;

    private String            value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
