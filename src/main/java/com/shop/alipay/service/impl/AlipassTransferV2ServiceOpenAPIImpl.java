/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.shop.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayPassInstanceAddRequest;
import com.alipay.api.request.AlipayPassInstanceUpdateRequest;
import com.alipay.api.request.AlipayPassTemplateAddRequest;
import com.alipay.api.request.AlipayPassTemplateUpdateRequest;
import com.alipay.api.response.AlipayPassInstanceAddResponse;
import com.alipay.api.response.AlipayPassInstanceUpdateResponse;
import com.alipay.api.response.AlipayPassTemplateAddResponse;
import com.alipay.api.response.AlipayPassTemplateUpdateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.alipay.enums.RecognitionTypeEnum;
import com.shop.alipay.enums.Result;
import com.shop.alipay.model.request.BaseAddRequest;
import com.shop.alipay.model.request.BaseRequest;
import com.shop.alipay.model.request.instance.AddPassInstanceRequest;
import com.shop.alipay.model.request.instance.UpdatePassInstanceRequest;
import com.shop.alipay.model.request.template.TemplateCreateRequest;
import com.shop.alipay.model.request.template.TemplateModifyRequest;
import com.shop.alipay.model.result.BaseResult;
import com.shop.alipay.service.AlipassTransferV2Service;
import com.shop.alipay.utils.HttpResponseBuilderV2;
import com.shop.alipay.utils.StringUtil;

/**
 * 走OPENAPI通道，将Alipass上送到支付宝钱包
 * @author mingqiu.gmq
 * @version $Id: AlipassTransferV2ServiceOpenAPIImpl.java, v 0.1 2015年6月25日 下午3:49:50 mingqiu.gmq Exp $
 */
public class AlipassTransferV2ServiceOpenAPIImpl implements AlipassTransferV2Service {

    //请求对象
    private AlipayClient client = null;

    /* (non-Javadoc)
     * @see com.alipay.alipass.sdk.service.AlipassTransferV2Service#init(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void init(String apiUrl, String appId, String appKey,String format,String charset,String alipayPublicKey) {
        client = new DefaultAlipayClient(apiUrl, appId, appKey, format,charset,alipayPublicKey);
    }

    /** 
     * @see com.alipay.alipass.sdk.service.AlipassTransferV2Service#createTemplate(com.alipay.alipass.sdk.model.request.template.TemplateCreateRequest)
     */
    @Override
    public AlipayPassTemplateAddResponse createTemplate(TemplateCreateRequest request)
                                                                                 throws AlipayApiException,
                                                                                 Exception {
        //请求对象
        AlipayPassTemplateAddRequest templateAddRequest = new AlipayPassTemplateAddRequest();

        BaseResult result = this.baseValidate(request);
        if (result.getResult() != Result.SUCCESS) {
            return HttpResponseBuilderV2.buildErrResponse(templateAddRequest, result);
        }
        if (StringUtil.isBlank(request.getContent())) {
            return HttpResponseBuilderV2.buildErrResponse(templateAddRequest,
                Result.EVI_ESSENTIAL_FIELDS_NOT_EXIST.name(), "模版内容为空");
        }
        if (StringUtil.isBlank(request.getLogo())) {
            return HttpResponseBuilderV2.buildErrResponse(templateAddRequest,
                Result.EVI_ESSENTIAL_FIELDS_NOT_EXIST.name(), "模版的logo.png图片地址为空");
        }
        if (StringUtil.isBlank(request.getUniqueId())) {
            return HttpResponseBuilderV2.buildErrResponse(templateAddRequest,
                Result.EVI_ESSENTIAL_FIELDS_NOT_EXIST.name(), "模版的外部唯一ID【unique_id】为空");
        }

        //组装请求参数
        templateAddRequest.putOtherTextParam("app_auth_token", request.getAppAuthToken());
        templateAddRequest.setBizContent("{\"unique_id\":\"" + request.getUniqueId()
                                         + "\",\"tpl_content\":" + request.toJsonString() + "}");
        return client.execute(templateAddRequest);
    }

    /**
     * 基本入参校验
     * @param request   请求入参对象
     * @return  校验结果
     */
    private BaseResult baseValidate(BaseRequest request) {

        BaseResult result = new BaseResult();
        // 判断必传项
        if (request == null) {
            result.setResult(Result.ESSENTIAL_FIELDS_NOT_EXIST);
            result.setExtDesc("请求对象为空");
            return result;
        }
        return result;
    }

    /** 
     * @see com.alipay.alipass.sdk.service.AlipassTransferV2Service#modifyTemplate(com.alipay.alipass.sdk.model.request.template.TemplateModifyRequest)
     */
    @Override
    public AlipayPassTemplateUpdateResponse modifyTemplate(TemplateModifyRequest request)
                                                                                     throws AlipayApiException,
                                                                                     Exception {
        //请求对象
        AlipayPassTemplateUpdateRequest tplUpdateRequest = new AlipayPassTemplateUpdateRequest();

        BaseResult result = this.baseValidate(request);
        if (result.getResult() != Result.SUCCESS) {
            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest, result);
        }
        if (StringUtil.isBlank(request.getTemplateId())) {
            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest,
                Result.ESSENTIAL_FIELDS_NOT_EXIST.name(), "templateId为空");
        }
        if (StringUtil.isBlank(request.getContent())) {
            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest,
                Result.ESSENTIAL_FIELDS_NOT_EXIST.name(), "模版内容为空");
        }

        //组装请求参数
        tplUpdateRequest.putOtherTextParam("app_auth_token", request.getAppAuthToken());
        tplUpdateRequest.setBizContent("{\"tpl_id\":\"" + request.getTemplateId()
                                       + "\",\"tpl_content\":" + request.toJsonString() + "}");
        return client.execute(tplUpdateRequest);
    }

    /** 
     * @see com.alipay.alipass.sdk.service.AlipassTransferV2Service#addPassInstance(com.alipay.alipass.sdk.model.request.instance.AddPassInstanceRequest)
     */
    @Override
    public AlipayPassInstanceAddResponse addPassInstance(AddPassInstanceRequest request)
                                                                               throws AlipayApiException,
                                                                               Exception {

        //请求对象
        AlipayPassInstanceAddRequest tplAddRequest = new AlipayPassInstanceAddRequest();
        BaseResult result = this.validAddParams(request,
            (request == null ? null : request.getUserType()));
        if (result.getResult() != Result.SUCCESS) {
            return HttpResponseBuilderV2.buildErrResponse(tplAddRequest, result);
        }
        // 判断必传项
        if (StringUtil.isBlank(request.getTemplateId())) {
            return HttpResponseBuilderV2.buildErrResponse(tplAddRequest,
                Result.ESSENTIAL_FIELDS_NOT_EXIST.name(), "模版ID为空");
        }

        String recognitionInfo = null, tplParams = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            recognitionInfo = mapper.writeValueAsString(request.getUserTypeParams());
            tplParams = mapper.writeValueAsString(request.getTemplateParamValuePair());
        } catch (Exception e) {
            return HttpResponseBuilderV2.buildErrResponse(tplAddRequest,
                Result.ILLEGAL_PARAM.name(), "PARAMS|TEMPLATE_PARAM_VALUE_PAIR为空或者格式必须为json格式");
        }
        String bizContent = "{\"recognition_type\":\"" + request.getUserType().getCode()
                            + "\", \"tpl_id\":\"" + request.getTemplateId()
                            + "\", \"recognition_info\":" + recognitionInfo + ",\"tpl_params\":"
                            + tplParams + "}";
        tplAddRequest.putOtherTextParam("app_auth_token", request.getAppAuthToken());
        tplAddRequest.setBizContent(bizContent);
        return client.execute(tplAddRequest);

    }

    /** 
     * @see com.alipay.alipass.sdk.service.AlipassTransferV2Service#updateByTemplate(com.alipay.alipass.sdk.model.request.instance.UpdatePassInstanceRequest)
     */
    @Override
    public AlipayPassInstanceUpdateResponse updatePassInstance(UpdatePassInstanceRequest request)
                                                                                          throws AlipayApiException,
                                                                                          Exception {

        //请求对象
        AlipayPassInstanceUpdateRequest tplUpdateRequest = new AlipayPassInstanceUpdateRequest();

        // 判断必传项
        BaseResult result = this.baseValidate(request);
        if (result.getResult() != Result.SUCCESS) {
            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest, result);
        }
        if (StringUtil.isBlank(request.getSerialNumber())
            || ((request.getTemplateParamValuePair() == null || request.getTemplateParamValuePair()
                .isEmpty()) && request.getStatus() == null)) {

            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest,
                Result.ESSENTIAL_FIELDS_NOT_EXIST.name(),
                "业务参数为空serialNumber|TemplateParamValuePair&status同时为空");
        }

        String tplParams = null;
        try {
            tplParams = new ObjectMapper().writeValueAsString(request.getTemplateParamValuePair());
        } catch (Exception e) {

            return HttpResponseBuilderV2.buildErrResponse(tplUpdateRequest,
                Result.ILLEGAL_PARAM.name(), "templateParamValuePair必须为json格式");
        }

        String status = "";
        if (request.getStatus() != null) {
            status = request.getStatus().getCode();
        }
        String bizContent = "{\"status\":\"" + status + "\", \"verify_type\":\""
                            + request.getVerifyType() + "\", \"tpl_params\":" + tplParams
                            + ", \"channel_id\":\"" + request.getChannelId()
                            + "\",\"serial_number\":\"" + request.getSerialNumber()
                            + "\",\"verify_code\":\"" + request.getVerifyCode() + "\"}";
        tplUpdateRequest.setBizContent(bizContent);
        tplUpdateRequest.putOtherTextParam("app_auth_token", request.getAppAuthToken());
        return client.execute(tplUpdateRequest);
    }

    @SuppressWarnings("deprecation")
    private BaseResult validAddParams(BaseAddRequest request, RecognitionTypeEnum userType) {

        BaseResult result = new BaseResult();
        result = this.baseValidate(request);
        if (result.getResult() != Result.SUCCESS) {
            return result;
        }
        if (request.getUserType() == null || request.getUserTypeParams() == null
            || request.getUserTypeParams().isEmpty()) {
            result.setResult(Result.ESSENTIAL_FIELDS_NOT_EXIST);
            result.setExtDesc("UserType|UserTypeParams数据为空");
            return result;
        }
        switch (userType) {
            case TRADE:
                String pid = request.getUserTypeParams().get(BaseAddRequest.PARTNER_ID);
                String outTradeNo = request.getUserTypeParams().get(BaseAddRequest.OUT_TRADE_NO);
                if (StringUtil.isBlank(pid) || StringUtil.isBlank(outTradeNo)) {
                    result.setResult(Result.ILLEGAL_PARAM);
                    result.setExtDesc("PARTNER_ID|OUT_TRADE_NO数据为空");
                }
                break;
            case USERID:
                String userId = request.getUserTypeParams().get(BaseAddRequest.USER_ID);
                if (StringUtil.isBlank(userId)) {
                    result.setResult(Result.ILLEGAL_PARAM);
                    result.setExtDesc("USER_ID数据为空");
                }
                break;
            case MOBILE:
                String mobile = request.getUserTypeParams().get(BaseAddRequest.MOBILE);
                if (StringUtil.isBlank(mobile)) {
                    result.setResult(Result.ILLEGAL_PARAM);
                    result.setExtDesc("MOBILE数据为空");
                }
                break;
            case OPENID:
                String openId = request.getUserTypeParams().get(BaseAddRequest.OPEN_ID);
                if (StringUtil.isBlank(openId)) {
                    result.setResult(Result.ILLEGAL_PARAM);
                    result.setExtDesc("OPEN_ID数据为空");
                }
                break;
            default:
                result.setResult(Result.ILLEGAL_PARAM);
                result.setExtDesc("userType类型不支持");
                break;
        }
        return result;
    }
}
