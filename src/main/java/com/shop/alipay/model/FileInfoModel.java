/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 文件属性Model
 * @author siyu.jsy
 * @version $Id: FileInfoModel.java,v 0.1 2013-5-2 下午7:14:38 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class FileInfoModel extends BaseModel {

    private static final long serialVersionUID = 8272731332120777536L;

    /**版本信息，目前为1 或者 2*/
    private String            formatVersion;

    /** 是否支持分享*/
    private boolean           canShare;

    /** 是否支持购买*/
    private boolean           canBuy           = false;

    /** 是否支持赠送 默认为可以赠送*/
    private boolean           canPresent       = true;

    /**渠道商交易唯一标识*/
    private String            serialNumber;

    /**卡预览页绑卡按钮url**/
    private String            bindCardUrl;

    /**是否预定模式 支持变量模式**/
    private String            reservation;

    /** true/false 表示是否支持打车*/
    private String            supportTaxi;

    /**如果支撑打车，则需要提供快的打车或者滴滴打车的跳转链接**/
    private String            taxiSchemaUrl;

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public boolean isCanPresent() {
        return canPresent;
    }

    public void setCanPresent(boolean canPresent) {
        this.canPresent = canPresent;
    }

    public String getBindCardUrl() {
        return bindCardUrl;
    }

    public void setBindCardUrl(String bindCardUrl) {
        this.bindCardUrl = bindCardUrl;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public void setSupportTaxi(String supportTaxi) {
        this.supportTaxi = supportTaxi;
    }

    public void setTaxiSchemaUrl(String taxiSchemaUrl) {
        this.taxiSchemaUrl = taxiSchemaUrl;
    }

    public String getSupportTaxi() {
        return supportTaxi;
    }

    public String getTaxiSchemaUrl() {
        return taxiSchemaUrl;
    }

}
