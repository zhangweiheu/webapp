/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 渠道商属性Model
 * @author siyu.jsy
 * @version $Id: MerchantModel.java,v 0.1 2013-5-2 下午5:35:03 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class MerchantModel extends BaseModel {

    private static final long serialVersionUID = 5312948338503683164L;

    /**渠道商全称*/
    @JsonProperty("mname")
    private String            merName;

    /**渠道商简称*/
    @JsonProperty("mshortName")
    private String            merShortName;

    /**渠道商地址*/
    @JsonProperty("maddr")
    private String            merAddres;

    /**渠道商电话*/
    @JsonProperty("mtel")
    private String            merTel;

    /**渠道商简介*/
    @JsonProperty("minfo")
    private String            merInfo;

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerShortName() {
        return merShortName;
    }

    public void setMerShortName(String merShortName) {
        this.merShortName = merShortName;
    }

    public String getMerAddres() {
        return merAddres;
    }

    public void setMerAddres(String merAddres) {
        this.merAddres = merAddres;
    }

    public String getMerTel() {
        return merTel;
    }

    public void setMerTel(String merTel) {
        this.merTel = merTel;
    }

    public String getMerInfo() {
        return merInfo;
    }

    public void setMerInfo(String merInfo) {
        this.merInfo = merInfo;
    }

}
