/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;


import com.shop.alipay.enums.Result;

/**
 * Alipass 生成结果
 * @author siyu.jsy
 * @version $Id: ResponseModel.java,v 0.1 2013-5-3 下午3:20:11 siyu.jsy Exp $
 */
public class ResponseModel extends BaseModel {

    private static final long serialVersionUID = 1833091869186717756L;

    /**生成alipass的结果*/
    private Result result;

    /**Base64之后的alipass文件*/
    private String            alipass;

    /**
     * alipassModel
     */
    private AlipassModel      alipassModel;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getAlipass() {
        return alipass;
    }

    public void setAlipass(String alipass) {
        this.alipass = alipass;
    }

    public AlipassModel getAlipassModel() {
        return alipassModel;
    }

    public void setAlipassModel(AlipassModel alipassModel) {
        this.alipassModel = alipassModel;
    }

}
