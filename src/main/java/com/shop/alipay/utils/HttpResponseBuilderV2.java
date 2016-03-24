/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.shop.alipay.utils;

import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayPassInstanceAddResponse;
import com.alipay.api.response.AlipayPassInstanceUpdateResponse;
import com.alipay.api.response.AlipayPassTemplateAddResponse;
import com.alipay.api.response.AlipayPassTemplateUpdateResponse;
import com.shop.alipay.model.result.BaseResult;

/**
 * Http请求错误码构建工具
 * 
 * @author junhua.pan
 * @version $Id: HttpResponse.java, v 0.1 2014-6-16 下午2:29:58 junhua.pan Exp $
 */
public class HttpResponseBuilderV2 {

    /**SUBCODE的前缀 在开发平台2.0中申请的卡券业务为KP*/
    private static final String SUBCODE_PREFIX = "KP.";

    /**2.0规范**/
    private static final String MSG            = "Business Failed";

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
        source.setMsg(MSG);
        source.setSubCode(SUBCODE_PREFIX + errCode);
        source.setSubMsg(errMsg);
        setSuccess(source, request);
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
        source.setMsg(MSG);
        source.setSubCode(SUBCODE_PREFIX + result.getResult().getResultValue());
        source.setSubMsg(result.getResult().getResultMsg() + "," + result.getExtDesc());
        setSuccess(source, request);
        return source;
    }

    /**
     * 设置Success
     * 
     * @param source
     * @param request
     */
    private static void setSuccess(Object source, Object request) {
        if (request instanceof AlipayPassTemplateAddResponse) {
            ((AlipayPassTemplateAddResponse) source).setSuccess("false");
        } else if (request instanceof AlipayPassTemplateUpdateResponse) {
            ((AlipayPassTemplateUpdateResponse) source).setSuccess("false");
        } else if (request instanceof AlipayPassInstanceAddResponse) {
            ((AlipayPassInstanceAddResponse) source).setSuccess("false");
        } else if (request instanceof AlipayPassInstanceUpdateResponse) {
            ((AlipayPassInstanceUpdateResponse) source).setSuccess("false");
        }
    }
}
