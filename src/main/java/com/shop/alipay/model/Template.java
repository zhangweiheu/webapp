/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * Alipass模版内容对象
 * 
 * @author junhua.pan
 * @version $Id: AlipassTemplate.java, v 0.1 2014-6-11 下午3:09:47 junhua.pan Exp $
 */
@JsonInclude(Include.NON_EMPTY)
public class Template implements Serializable {

    /**  序列号*/
    private static final long serialVersionUID = -3582931773840505764L;

    /**   logo.png的url信息*/
    private String            logo;

    /**   icon.png的url信息*/
    private String            icon;

    /**   strip.png的url信息*/
    private String            strip;

    /**   pass.json具体的内容*/
    private AlipassModel      content;

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

    public AlipassModel getContent() {
        return content;
    }

    public void setContent(AlipassModel content) {
        this.content = content;
    }

}
