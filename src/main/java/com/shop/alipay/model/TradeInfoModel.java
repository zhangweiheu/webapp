/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 交易属性Model
 * @author siyu.jsy
 * @version $Id: TradeInfoModel.java,v 0.1 2013-5-2 下午7:06:47 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class TradeInfoModel extends BaseModel {

    private static final long serialVersionUID = -8910364305978814287L;

    /**支付宝交易号*/
    private String            tradeNo;

    /**单张实际支付额*/
    private String            perVoucherBuyAmount;

    /**单张面额*/
    private String            perVoucherAmount;

    /**交易数量*/
    private String            voucherBuyCount;

    /**买家账号*/
    private String            buyerUserId;

    /**手动退款比例*/
    private String            manualRefundRatio;

    /**自动退款比例*/
    private String            autoRefundRatio;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPerVoucherBuyAmount() {
        return perVoucherBuyAmount;
    }

    public void setPerVoucherBuyAmount(String perVoucherBuyAmount) {
        this.perVoucherBuyAmount = perVoucherBuyAmount;
    }

    public String getPerVoucherAmount() {
        return perVoucherAmount;
    }

    public void setPerVoucherAmount(String perVoucherAmount) {
        this.perVoucherAmount = perVoucherAmount;
    }

    public String getVoucherBuyCount() {
        return voucherBuyCount;
    }

    public void setVoucherBuyCount(String voucherBuyCount) {
        this.voucherBuyCount = voucherBuyCount;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getManualRefundRatio() {
        return manualRefundRatio;
    }

    public void setManualRefundRatio(String manualRefundRatio) {
        this.manualRefundRatio = manualRefundRatio;
    }

    public String getAutoRefundRatio() {
        return autoRefundRatio;
    }

    public void setAutoRefundRatio(String autoRefundRatio) {
        this.autoRefundRatio = autoRefundRatio;
    }

}
