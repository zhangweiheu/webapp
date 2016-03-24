/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.request.template;


import com.shop.alipay.model.request.BaseRequest;

/**
 * Alipass模版变更请求参数类
 * 
 * @author junhua.pan
 * @version $Id: TemplateModifyRequest.java, v 0.1 2014-5-30 下午2:40:32 junhua.pan Exp $
 */
public class TemplateModifyRequest extends BaseRequest {

    /**  序列号*/
    private static final long serialVersionUID = -4412350965186494613L;

    /** 需要变更的Alipass模版ID【必传】*/
    private String            templateId;

    /** logo.png的url【必传】*/
    private String            logo;

    /** icon.png的url信息*/
    private String            icon;

    /** strip.png的url信息*/
    private String            strip;

    /** pass.json具体的内容【必传】*/
    private String            content;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStrip() {
        return strip;
    }

    public void setStrip(String strip) {
        this.strip = strip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 生成需要解析的JSON字串
     * 
     * @return
     */
    public String toJsonString() {
        StringBuffer buffer = new StringBuffer("{");

        buffer.append("\"logo\":");
        buffer.append((this.getLogo() == null) ? this.getLogo() : ("\"" + this.getLogo() + "\""));

        buffer.append(",\"strip\":");
        buffer
            .append((this.getStrip() == null) ? this.getStrip() : ("\"" + this.getStrip() + "\""));

        buffer.append(",\"icon\":");
        buffer.append((this.getIcon() == null) ? this.getIcon() : ("\"" + this.getIcon() + "\""));

        buffer.append(",\"content\":").append(
            (this.getContent() == null) ? null : (this.getContent().replaceAll("[\\n]", "\\\\n")));
        buffer.append("}");
        return buffer.toString();
    }

}
