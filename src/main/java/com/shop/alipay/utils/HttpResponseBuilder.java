/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.utils;

import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.shop.alipay.model.result.BaseResult;

/**
 * Http请求错误码构建工具
 * 
 * @author junhua.pan
 * @version $Id: HttpResponse.java, v 0.1 2014-6-16 下午2:29:58 junhua.pan Exp $
 */
public class HttpResponseBuilder {

    /**
     * 构建错误返回结果
     * 
     * @param request   http接口请求对象
     * @param errCode   错误码
     * @param errMsg    错误描述
     * @return 接口返回结果
     * @throws Exception 异常
     */
    public static <T extends AlipayResponse> T buildErrResponse(AlipayRequest<T> request,
                                                                String errCode, String errMsg)
                                                                                              throws Exception {
        T source = request.getResponseClass().newInstance();
        source.setErrorCode(errCode);
        source.setMsg(errMsg);
        source.setSubCode("KP." + errCode);
        source.setSubMsg(errMsg);
        return source;
    }

    /**
     * 构建错误返回结果
     * 
     * @param request   http接口请求对象
     * @param result    结果对象
     * @return 接口返回结果
     * @throws IllegalAccessException 
     * @throws Exception 
     * @throws Exception 异常
     */
    public static <T extends AlipayResponse> T buildErrResponse(AlipayRequest<T> request,
                                                                BaseResult result) throws Exception {
        T source = request.getResponseClass().newInstance();
        source.setErrorCode(result.getResult().name());
        source.setMsg(result.getExtDesc());
        source.setSubCode(result.getSubErrCode());
        source.setSubMsg(result.getSubErrMsg());
        return source;
    }
}
