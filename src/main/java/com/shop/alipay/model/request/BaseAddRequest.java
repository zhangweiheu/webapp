/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.request;


import com.shop.alipay.enums.RecognitionTypeEnum;

import java.util.Map;

/**
 * 卡券添加的请求参数基类
 * 
 * @author junhua.pan
 * @version $Id: BaseAddRequest.java, v 0.1 2014-5-30 下午3:45:25 junhua.pan Exp $
 */
public class BaseAddRequest extends BaseRequest {

    /**  序列号*/
    private static final long   serialVersionUID = -5808544187222591036L;

    /**  userType为ALIPAY_TRADE时，params参数必须包含以下两个key*/
    public static final String  PARTNER_ID       = "partner_id";
    public static final String  OUT_TRADE_NO     = "out_trade_no";

    /**  userType为ALIPAY_TRADE时，params参数必须包含USER_ID这个key*/
    public static final String  USER_ID          = "user_id";

    /**  userType为ALIPAY_MOBILE时，params参数必须包含MOBILE这个key*/
    public static final String  MOBILE           = "mobile";

    /**  userType为ALIPAY_OPENID时，params参数必须包含OPEN_ID这个key*/
    public static final String  OPEN_ID          = "open_id";

    /**  添加到支付宝用户的用户信息的参数类型*/
    private RecognitionTypeEnum userType;

    /**  用户信息的参数类型对应的参数键值对
     * 当 userType=TRADE时， params必须包含交易相关的属性：例如{“partner_id”:”208810211463****”,“out_trade_no”:”12****”}
     * 当userType=USERID时， params必须包含user_id的信息：例如{“user_id”:”208810211463****“ } 
     * 当userType=MOBILE时， params包含手机号：例如{“mobile”:”136********“ } 
     * 当userType=OPENID时， params包含手机号：例如{“open_id”:”ajqw98917712oi3jlkqwjleqwe1“ } 
     */
    private Map<String, String> userTypeParams;

    public RecognitionTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(RecognitionTypeEnum userType) {
        this.userType = userType;
    }

    public Map<String, String> getUserTypeParams() {
        return userTypeParams;
    }

    public void setUserTypeParams(Map<String, String> userTypeParams) {
        this.userTypeParams = userTypeParams;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
