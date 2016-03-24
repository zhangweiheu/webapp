/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 门店模型
 * <li>不建议使用</li>
 * <li>请使用{@see CityAndShopInfoModel}</li>
 * 
 * @author junhua.pan
 * @version $Id: ShopInfoModel.java, v 0.1 2014-7-16 下午3:05:05 junhua.pan Exp $
 */
@JsonInclude(Include.NON_NULL)
public class ShopInfoModel extends BaseModel {

    /**  序列号*/
    private static final long serialVersionUID = 8632444468353188446L;

    /** 门店ID*/
    @JsonProperty("shopId")
    private String            shopId;

    /**门店海拔数据*/
    @JsonProperty("altitude")
    private String            locAltitude;

    /**门店经度数据*/
    @JsonProperty("longitude")
    private String            locLongitude;

    /**门店纬度数据*/
    @JsonProperty("latitude")
    private String            locLatitude;

    /**门店电话*/
    @JsonProperty("tel")
    private String            locTel;

    /**门店地址*/
    @JsonProperty("addr")
    private String            locAddress;

    /**门店名称*/
    @JsonProperty("relevantText")
    private String            locRelevantText;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getLocAltitude() {
        return locAltitude;
    }

    public void setLocAltitude(String locAltitude) {
        this.locAltitude = locAltitude;
    }

    public String getLocLongitude() {
        return locLongitude;
    }

    public void setLocLongitude(String locLongitude) {
        this.locLongitude = locLongitude;
    }

    public String getLocLatitude() {
        return locLatitude;
    }

    public void setLocLatitude(String locLatitude) {
        this.locLatitude = locLatitude;
    }

    public String getLocTel() {
        return locTel;
    }

    public void setLocTel(String locTel) {
        this.locTel = locTel;
    }

    public String getLocAddress() {
        return locAddress;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public String getLocRelevantText() {
        return locRelevantText;
    }

    public void setLocRelevantText(String locRelevantText) {
        this.locRelevantText = locRelevantText;
    }

}
