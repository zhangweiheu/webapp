/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 渠道商门店位置Model
 * 城市门店集合
 * 
 * @author siyu.jsy
 * @author junhua.pan
 * 
 * @version $Id: LocationModel.java,v 0.1 2013-5-2 下午2:29:21 siyu.jsy Exp $
 */
public class LocationModel extends BaseModel {

    /** 序列号*/
    private static final long   serialVersionUID = -4922119876399343650L;

    /** 第一层级-城市ID*/
    @JsonProperty("cityId")
    private String              cityId;

    /** 第一层级-城市名称*/
    @JsonProperty("city")
    private String              city;

    /** 第二层级-门店集合*/
    @JsonProperty("shops")
    private List<ShopInfoModel> shops;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ShopInfoModel> getShops() {
        return shops;
    }

    public void setShops(List<ShopInfoModel> shops) {
        this.shops = shops;
    }

}
