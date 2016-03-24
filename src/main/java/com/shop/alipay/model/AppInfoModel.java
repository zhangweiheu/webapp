/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 应用属性Model
 * @author siyu.jsy
 * @version $Id: AppInfoModel.java,v 0.1 2013-5-2 下午7:26:23 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class AppInfoModel extends BaseModel {

    private static final long serialVersionUID = 5974448177348661513L;

    /**显示的应用名称*/
    @JsonProperty("label")
    private String            appLabel;

    /**相关文案说明*/
    @JsonProperty("message")
    private String            appMessage;

    /**应用相关信息*/
    private AppDetailModel    app;

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }

    public AppDetailModel getApp() {
        return app;
    }

    public void setApp(AppDetailModel app) {
        this.app = app;
    }

}
