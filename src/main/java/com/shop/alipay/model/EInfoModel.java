/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * Pass特性Model
 * @author siyu.jsy
 * @version $Id: EInfoModel.java,v 0.1 2013-5-2 下午4:51:10 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_DEFAULT)
public class EInfoModel extends BaseModel {

    private static final long    serialVersionUID = -5229642842366323834L;

    /**Logo说明*/
    private String               logoText;

    /**辅助Logo说明*/
    private String               secondLogoText;

    /**显示内容*/
    private List<EInfoUnitModel> headFields;

    /**第一区域块展示内容*/
    private List<EInfoUnitModel> primaryFields;

    /**第二区域块展示内容*/
    private List<EInfoUnitModel> secondaryFields;

    /**辅助区域块展示内容*/
    private List<EInfoUnitModel> auxiliaryFields;

    /**背后区域块展示内容*/
    private List<EInfoUnitModel> backFields;

    /**商户自定义配置区域**/
    private List<EInfoUnitModel> customFields;

    /**优惠内容 例如：30.00元代金券 , 9.2折,麦乐鸡翅 2个等*/
    private String               discountContent;

    /**使用限制简短描述 例如：消费满100元可用*/
    private String               useLimitDesc;

    /***专享折扣须知信息 例如：酒水不打折*/
    private String               discountNotice;

    public String getLogoText() {
        return logoText;
    }

    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }

    public String getSecondLogoText() {
        return secondLogoText;
    }

    public void setSecondLogoText(String secondLogoText) {
        this.secondLogoText = secondLogoText;
    }

    public List<EInfoUnitModel> getHeadFields() {
        return headFields;
    }

    public void setHeadFields(List<EInfoUnitModel> headFields) {
        this.headFields = headFields;
    }

    public List<EInfoUnitModel> getPrimaryFields() {
        return primaryFields;
    }

    public void setPrimaryFields(List<EInfoUnitModel> primaryFields) {
        this.primaryFields = primaryFields;
    }

    public List<EInfoUnitModel> getSecondaryFields() {
        return secondaryFields;
    }

    public void setSecondaryFields(List<EInfoUnitModel> secondaryFields) {
        this.secondaryFields = secondaryFields;
    }

    public List<EInfoUnitModel> getAuxiliaryFields() {
        return auxiliaryFields;
    }

    public void setAuxiliaryFields(List<EInfoUnitModel> auxiliaryFields) {
        this.auxiliaryFields = auxiliaryFields;
    }

    public List<EInfoUnitModel> getBackFields() {
        return backFields;
    }

    public void setBackFields(List<EInfoUnitModel> backFields) {
        this.backFields = backFields;
    }

    public List<EInfoUnitModel> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<EInfoUnitModel> customFields) {
        this.customFields = customFields;
    }

    public String getDiscountContent() {
        return discountContent;
    }

    public void setDiscountContent(String discountContent) {
        this.discountContent = discountContent;
    }

    public String getUseLimitDesc() {
        return useLimitDesc;
    }

    public void setUseLimitDesc(String useLimitDesc) {
        this.useLimitDesc = useLimitDesc;
    }

    public String getDiscountNotice() {
        return discountNotice;
    }

    public void setDiscountNotice(String discountNotice) {
        this.discountNotice = discountNotice;
    }

}
