/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 渠道属性Model
 * @author siyu.jsy
 * @version $Id: PlatformModel.java,v 0.1 2013-5-2 下午6:57:35 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class PlatformModel extends BaseModel {

    private static final long serialVersionUID = 6610219025017731612L;

    /**渠道商在支付包的partnerID*/
    private String            channelID;

    /**渠道商服务URL*/
    private String            webServiceUrl;

    /**渠道场景，包括public、client、alipass_sm、server*/
    private String            channelScene;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getWebServiceUrl() {
        return webServiceUrl;
    }

    public void setWebServiceUrl(String webServiceUrl) {
        this.webServiceUrl = webServiceUrl;
    }

    public String getChannelScene() {
        return channelScene;
    }

    public void setChannelScene(String channelScene) {
        this.channelScene = channelScene;
    }

}
