/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 提醒属性Model
 * @author siyu.jsy
 * @version $Id: RemindInfoModel.java,v 0.1 2013-5-2 下午5:26:20 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class RemindInfoModel extends BaseModel {

    private static final long serialVersionUID = -5320084434098157126L;

    /**提醒提前时间*/
    private String            offset;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
