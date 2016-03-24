/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * pass.json 对应的Model
 * @author siyu.jsy
 * @version $Id: AlipassModel.java,v 0.1 2013-5-2 下午8:56:14 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class AlipassModel extends BaseModel {

    private static final long serialVersionUID = 4811266601180245314L;

    /**基础属性*/
    private EVoucherInfoModel evoucherInfo;

    /**渠道商属性*/
    private MerchantModel     merchant;

    /**渠道属性*/
    private PlatformModel     platform;

    /**交易属性*/
    private TradeInfoModel    tradeInfo;

    /**样式属性*/
    private StyleModel        style;

    /**文件属性*/
    private FileInfoModel     fileInfo;

    /** 会员卡开卡前预览权益信息*/
    private List<String>      featureDescriptions;

    /**应用属性*/
    private AppInfoModel      appInfo;

    /**
     * 标识SDK版本,即调用方来源。如果为Alipass Source调用，则此字段需要重新赋值为传来的数据
     */
    private String            source           = "Java1.3.2";
    /**
     * 是否使用支付宝发码、核销
     * 目前支持格式及类型（可选并支持多选）：
     *  "alipayVerify": "wave,barcode,qrcode,text"
     *  从OperationFormatType 中getCode() 
     */
    private List<String>      alipayVerify;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public EVoucherInfoModel getEvoucherInfo() {
        return evoucherInfo;
    }

    public void setEvoucherInfo(EVoucherInfoModel evoucherInfo) {
        this.evoucherInfo = evoucherInfo;
    }

    public MerchantModel getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModel merchant) {
        this.merchant = merchant;
    }

    public PlatformModel getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformModel platform) {
        this.platform = platform;
    }

    public TradeInfoModel getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(TradeInfoModel tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public StyleModel getStyle() {
        return style;
    }

    public void setStyle(StyleModel style) {
        this.style = style;
    }

    public FileInfoModel getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfoModel fileInfo) {
        this.fileInfo = fileInfo;
    }

    public AppInfoModel getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfoModel appInfo) {
        this.appInfo = appInfo;
    }

    public List<String> getAlipayVerify() {
        return alipayVerify;
    }

    public void setAlipayVerify(List<String> alipayVerify) {
        this.alipayVerify = alipayVerify;
    }

    public List<String> getFeatureDescriptions() {
        return featureDescriptions;
    }

    /**
     * 设置会员卡开卡前预览权益信息
     * 
     * @param featureDescriptions
     */
    public void setFeatureDescriptions(List<String> featureDescriptions) {
        this.featureDescriptions = featureDescriptions;
    }

}
