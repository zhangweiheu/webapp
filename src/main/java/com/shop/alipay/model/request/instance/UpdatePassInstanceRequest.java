/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.request.instance;


import com.shop.alipay.enums.PassStatus;
import com.shop.alipay.model.request.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过模版更新卡券内容的请求参数对象
 * 
 * @author junhua.pan
 * @version $Id: UpdatePassInstanceRequest.java, v 0.1 2014-5-30 下午2:36:43 junhua.pan Exp $
 */
public class UpdatePassInstanceRequest extends BaseRequest {

    /**  序列号*/
    private static final long   serialVersionUID       = 2782059654498925765L;

    /**  pass唯一标识*/
    private String              serialNumber;

    /**  需要更新的alipass模版动态键值对*/
    private Map<String, String> templateParamValuePair = new HashMap<String, String>();

    /**代理商替代商户发放卡券之后，替代商户更新卡券时，需要传入商户的pid/appId*/
    private String              channelId;

    /**  pass状态*/
    private PassStatus status;

    /**  当status为USED时：*/
    private String              verifyCode;

    /**  */
    private String              verifyType;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Map<String, String> getTemplateParamValuePair() {
        return templateParamValuePair;
    }

    public void setTemplateParamValuePair(Map<String, String> templateParamValuePair) {
        this.templateParamValuePair = templateParamValuePair;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public PassStatus getStatus() {
        return status;
    }

    public void setStatus(PassStatus status) {
        this.status = status;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }
}
