/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.shop.alipay.enums.PassType;
import com.shop.alipay.enums.ProductType;

import java.util.List;

/**
 * 基础属性Model
 * @author siyu.jsy
 * @version $Id: EVoucherInfoModel.java,v 0.1 2013-5-2 下午2:30:01 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class EVoucherInfoModel extends BaseModel {

    private static final long    serialVersionUID = 3466045026575811175L;

    /**pass对应的商品ID*/
    private String               goodsId;

    /**pass名称*/
    private String               title;

    /**pass类型*/
    private PassType type;

    /**pass子类型*/
    private ProductType product;

    /**pass生效时间*/
    private String               startDate;

    /**pass失效时间*/
    private String               endDate;

    /**操作信息*/
    private List<OperationModel> operation;

    /**Pass特性*/
    private EInfoModel           einfo;

    /**门店位置*/
    private List<LocationModel>  locations;

    /**提醒信息*/
    private RemindInfoModel      remindInfo;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PassType getType() {
        return type;
    }

    public void setType(PassType type) {
        this.type = type;
    }

    public ProductType getProduct() {
        return product;
    }

    public void setProduct(ProductType product) {
        this.product = product;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<OperationModel> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationModel> operation) {
        this.operation = operation;
    }

    public EInfoModel getEinfo() {
        return einfo;
    }

    public void setEinfo(EInfoModel einfo) {
        this.einfo = einfo;
    }

    public List<LocationModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationModel> locations) {
        this.locations = locations;
    }

    public RemindInfoModel getRemindInfo() {
        return remindInfo;
    }

    public void setRemindInfo(RemindInfoModel remindInfo) {
        this.remindInfo = remindInfo;
    }

}
