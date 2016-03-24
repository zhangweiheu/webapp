/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.request.instance;


import com.shop.alipay.model.request.BaseAddRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 模版方式添加卡券的请求入参对象
 * 
 * @author junhua.pan
 * @version $Id: AddPassInstanceRequest.java, v 0.1 2014-5-30 下午1:45:33 junhua.pan Exp $
 */
public class AddPassInstanceRequest extends BaseAddRequest {

    /** 序列号 */
    private static final long   serialVersionUID       = -5072117992410803863L;

    //------------------------业务参数--------------------------
    /**  alipass模版ID*/
    private String              templateId;

    /**  alipass模版所属用户ID*/
    private String              templateUserId;

    /**  alipass模版动态参数键值对*/
    private Map<String, String> templateParamValuePair = new HashMap<String, String>();

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateUserId() {
        return templateUserId;
    }

    public void setTemplateUserId(String templateUserId) {
        this.templateUserId = templateUserId;
    }

    public Map<String, String> getTemplateParamValuePair() {
        return templateParamValuePair;
    }

    public void setTemplateParamValuePair(Map<String, String> templateParamValuePair) {
        this.templateParamValuePair = templateParamValuePair;
    }

    @Override
    public String toString() {
        return new StringBuffer(super.toString()).append("\t[业务参数:").append("userType=")
            .append(super.getUserType()).append(";params=").append(super.getUserTypeParams())
            .append(";templateId=").append(templateId).append(";templateUserId=")
            .append(templateUserId).append(";templateParamValuePair=")
            .append(templateParamValuePair).append("]").toString();
    }
}
