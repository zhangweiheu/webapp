/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.model.result;


import com.shop.alipay.enums.Result;

import java.io.Serializable;

/**
 * 检验结果对象
 * 
 * @author junhua.pan
 * @version $Id: BaseResult.java, v 0.1 2014-6-16 下午3:10:08 junhua.pan Exp $
 */
public class BaseResult implements Serializable {

    /**  序列号*/
    private static final long serialVersionUID = 5780770534274177015L;

    /** 校验结果*/
    private Result            result           = Result.SUCCESS;

    /** 扩展结果描述*/
    private String            extDesc;

    /** 扩展错误码*/
    private String            subErrCode;

    /** 扩展错误提示信息*/
    private String            subErrMsg;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getExtDesc() {
        return extDesc;
    }

    public void setExtDesc(String extDesc) {
        this.extDesc = extDesc;
    }

    public String getSubErrCode() {
        return subErrCode;
    }

    public void setSubErrCode(String subErrCode) {
        this.subErrCode = subErrCode;
    }

    public String getSubErrMsg() {
        return subErrMsg;
    }

    public void setSubErrMsg(String subErrMsg) {
        this.subErrMsg = subErrMsg;
    }

}
