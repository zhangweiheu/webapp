/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.request;

import java.io.Serializable;

/**
 * 请求入参基类
 * 
 * @author junhua.pan
 * @version $Id: BaseRequest.java, v 0.1 2014-3-18 下午3:49:31 junhua.pan Exp $
 */
public class BaseRequest implements Serializable {

	/** 序列号 */
	private static final long serialVersionUID = 5224065665493112434L;

	/** 第三方授权码 */
	private String appAuthToken;

	/**
	 * Getter method for property <tt>appAuthToken</tt>.
	 * 
	 * @return property value of appAuthToken
	 */
	public String getAppAuthToken() {
		return appAuthToken;
	}

	/**
	 * Setter method for property <tt>appAuthToken</tt>.
	 * 
	 * @param appAuthToken
	 *            value to be assigned to property appAuthToken
	 */
	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}

	public String toString() {
		return "[请求：" + this.appAuthToken + "。]";
	}
}
