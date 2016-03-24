/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * APP详细信息
 * @author siyu.jsy
 * @version $Id: AppDetailModel.java,v 0.1 2013-5-6 下午5:03:18 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class AppDetailModel extends BaseModel {

    private static final long serialVersionUID = 7558368775928454313L;

    /**Android 版本下的应用ID*/
    private String            android_appid;

    /**IOS 版本下的应用ID*/
    private String            ios_appid;

    /**Android版本下的应用调用地址*/
    private String            android_launch;

    /**IOS 下的应用调用地址*/
    private String            ios_launch;

    /**Android下应用下载地址*/
    private String            android_download;

    /**IOS下应用下载地址*/
    private String            ios_download;

    public String getAndroid_appid() {
        return android_appid;
    }

    public void setAndroid_appid(String android_appid) {
        this.android_appid = android_appid;
    }

    public String getIos_appid() {
        return ios_appid;
    }

    public void setIos_appid(String ios_appid) {
        this.ios_appid = ios_appid;
    }

    public String getAndroid_launch() {
        return android_launch;
    }

    public void setAndroid_launch(String android_launch) {
        this.android_launch = android_launch;
    }

    public String getIos_launch() {
        return ios_launch;
    }

    public void setIos_launch(String ios_launch) {
        this.ios_launch = ios_launch;
    }

    public String getAndroid_download() {
        return android_download;
    }

    public void setAndroid_download(String android_download) {
        this.android_download = android_download;
    }

    public String getIos_download() {
        return ios_download;
    }

    public void setIos_download(String ios_download) {
        this.ios_download = ios_download;
    }

}
