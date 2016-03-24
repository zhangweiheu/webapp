/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.shop.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayPassInstanceAddResponse;
import com.alipay.api.response.AlipayPassInstanceUpdateResponse;
import com.alipay.api.response.AlipayPassTemplateAddResponse;
import com.alipay.api.response.AlipayPassTemplateUpdateResponse;
import com.shop.alipay.model.request.instance.AddPassInstanceRequest;
import com.shop.alipay.model.request.instance.UpdatePassInstanceRequest;
import com.shop.alipay.model.request.template.TemplateCreateRequest;
import com.shop.alipay.model.request.template.TemplateModifyRequest;

/**
 * 通过OPENAPI方式向Alipay服务器发送alipass相关数据，完成模版及卡券的添加和更新等操作
 *  <p>此sdk支持的接口:</p>
 *  <ul>模板添加/修改
 *      <li>alipay.pass.template.add</li>
 *      <li>alipay.pass.template.update</li>
 *  </ul>
 *  <ul>模版方式添加/更新卡券
 *      <li>alipay.pass.instance.add</li>
 *      <li>alipay.pass.instance.update</li>
 *  </ul>
 * @author mingqiu.gmq
 * @version $Id: AlipassTransferV2Service.java, v 0.1 2015年6月25日 下午3:42:37 mingqiu.gmq Exp $
 */
public interface AlipassTransferV2Service {

    /**
    * 服务初始化:
    * 
    * @param apiUrl    非空
    * @param appId     非空
    * @param appKey    非空
    */
    public void init(String apiUrl, String appId, String appKey, String format, String charset, String alipayPublicKey);

    /**
     * 创建Alipass模版接口-json字串方式
     * 
     * @param request {@see com.alipay.alipass.sdk.model.request.template.TemplateCreateRequest}
     * <ul>具体参数内容
     *  <li>
     *      @param logo             卡券展示的logo图片URL
     *      @param icon             卡券应用展台app的图片URL
     *      @param strip            卡券主体图片URL
     *      @param content          需要修改的alipass模版内容-JSON格式:结构见{@see com.alipay.alipass.sdk.jsonmodel.Template}content属性
     *      @param alipayApiUrl     支付宝URL地址
     *      @param appId            渠道（应用）ID。如果接入OPENAPI开放平台，则为商户接入开放平台时，开放平台为商户分配的ID
     *      @param privateKeyData   商户私钥      
     *  </li>
     * @return 模版创建结果
     * @throws AlipayApiException
     */
    public AlipayPassTemplateAddResponse createTemplate(TemplateCreateRequest request)
                                                                                      throws AlipayApiException,
                                                                                      Exception;

    /**
     * 修改Alipass模版接口-json字串方式
     * 注意：
     * <li>
     *  此接口目前只支持全量更新
     * </li>
     * 
     * @param request {@see com.alipay.alipass.sdk.model.request.template.TemplateModifyRequest}
     * <ul>具体参数内容
     *  <li>
     *      @param templateId       需要修改的alipass模版ID
     *      @param logo             卡券展示的logo图片URL
     *      @param icon             卡券应用展台app的图片URL
     *      @param strip            卡券主体图片URL
     *      @param content          需要修改的alipass模版内容-JSON格式:结构见{@see com.alipay.alipass.sdk.jsonmodel.Template}content属性
     *      @param alipayApiUrl     支付宝URL地址
     *      @param appId            渠道（应用）ID。如果接入OPENAPI开放平台，则为商户接入开放平台时，开放平台为商户分配的ID
     *      @param privateKeyData   商户私钥      
     *  </li>
     * @return 模版修改结果
     * @throws AlipayApiException
     */
    public AlipayPassTemplateUpdateResponse modifyTemplate(TemplateModifyRequest request)
                                                                                         throws AlipayApiException,
                                                                                         Exception;

    /**
     * alipass模版方式添加卡券实例
     * 
     * @param request {@see com.alipay.alipass.sdk.model.request.AddPassInstanceRequest}
     * <ul>具体参数内容
     *  <li>
     *      @param userType         用户识别类型{@see RecognitionTypeEnum}
     *      @param params           用户识别类型对应的具体参数键值对<br/>
     *          <p>当 userType=TRADE时， params必须包含交易相关的属性：例如{“partner_id”:”208810211463****”,“out_trade_no”:”12****”}</p>
     *          <p>当userType=USERID时， params必须包含user_id的信息：例如{“user_id”:”208810211463****“ } -- 不再支持</p>
     *          <p>当userType=MOBILE时， params包含手机号：例如{“mobile”:”136********“ } </p>
     *          <p>当userType=OPENID时， params包含手机号：例如{“open_id”:”akhdaqqywue987123kjahq8qwe87q“ } </p>
     *      @param templateId               alipass模版ID
     *      @prama templateUserId           alipass模版所属用户ID【alipass模版所有人】
     *      @prama templateParamValuePair   alipass模版动态参数键值对
     *      @param alipayApiUrl     支付宝URL地址
     *      @param appId            渠道（应用）ID。如果接入OPENAPI开放平台，则为商户接入开放平台时，开放平台为商户分配的ID
     *      @param privateKeyData       商户私钥
     *  </li>
     * </ul>
     * @return 新增结果
     * @throws AlipayApiException 请求异常
     */
    public AlipayPassInstanceAddResponse addPassInstance(AddPassInstanceRequest request)
                                                                                        throws AlipayApiException,
                                                                                        Exception;

    /**
     *  更新alipass内容-模版方式
     * 
     * @param request {@see com.alipay.alipass.sdk.model.request.UpdatePassInstanceRequest}
     * <ul>具体参数内容
     *  <li>
     *      @param serialNumber             alipass文件唯一标识
     *      @param templateParamValuePair   需要更新的alipass模版动态参数键值对
     *      @param alipayApiUrl             支付宝URL地址
     *      @param appId                    渠道（应用）ID。如果接入OPENAPI开放平台，则为商户接入开放平台时，开放平台为商户分配的ID
     *      @param privateKeyData           商户私钥      
     *  </li>
     * </ul>
     * @return 更新结果
     * @throws AlipayApiException 请求异常
     */
    public AlipayPassInstanceUpdateResponse updatePassInstance(UpdatePassInstanceRequest request)
                                                                                                 throws AlipayApiException,
                                                                                                 Exception;

}
