/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.shop.alipay.demo;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayPassInstanceAddResponse;
import com.alipay.api.response.AlipayPassInstanceUpdateResponse;
import com.alipay.api.response.AlipayPassTemplateAddResponse;
import com.alipay.api.response.AlipayPassTemplateUpdateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.alipay.enums.PassStatus;
import com.shop.alipay.enums.RecognitionTypeEnum;
import com.shop.alipay.model.request.BaseAddRequest;
import com.shop.alipay.model.request.instance.AddPassInstanceRequest;
import com.shop.alipay.model.request.instance.UpdatePassInstanceRequest;
import com.shop.alipay.model.request.template.TemplateCreateRequest;
import com.shop.alipay.model.request.template.TemplateModifyRequest;
import com.shop.alipay.service.AlipassTransferV2Service;
import com.shop.alipay.service.impl.AlipassTransferV2ServiceOpenAPIImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Alipass demo 2.0版本，对alipass的open api做简易封装。
 * 原生版本{@link com.alipay.alipass.sdk.demo}
 *
 * @author mingqiu.gmq
 * @version $Id: AlipassSDKOpenAPIV2Test.java, v 0.1 2015年6月25日 下午3:26:38
 *          mingqiu.gmq Exp $
 */
public class AlipassSDKOpenAPIV2Test {
    private static ObjectMapper JSON = new ObjectMapper();

    private static AlipassTransferV2Service transferService;

    // 设置app_auth_token参数，当isv代替商户发起接口调用时，需要传此参数；商户自主调用接口时，不需要传此参数
    private String appAuthToken = "";

    public static void main(String[] args) throws Exception {
        transferService = new AlipassTransferV2ServiceOpenAPIImpl();
        transferService.init(AlipassSDKConstants.ALIPAY_GATEWAY, AlipassSDKConstants.APP_ID, AlipassSDKConstants.PRIVATE_KEY, AlipassSDKConstants.FORMAT, AlipassSDKConstants.CHARSET, AlipassSDKConstants.ALIPAY_PUBLIC_KEY);

        AlipassSDKOpenAPIV2Test demo = new AlipassSDKOpenAPIV2Test();
        String templateId = "2016032321354504508882954";

        // 创建模板
        // templateId = demo.createTemplate();
        // System.out.println("templateId = " + templateId);

        // 修改模板
        //  demo.modifyTemplate(templateId);

        // 添加卡券实例
        demo.addPassInstance(templateId);

        // 修改卡券实例
        // demo.updatePassInstance(templateId);

    }

    /**
     * 2.0版本创建卡券模板
     *
     * @return
     * @throws Exception
     */
    private String createTemplate() throws Exception {
        String content =
                "{\"evoucherInfo\":{\"title\":\"$filmName$\",\"startDate\":\"$startDate$\",\"endDate\":\"$endDate$\",\"type\":\"eventTicket\",\"product\":\"groupon\","
                        + "\"operation\":["
                        + "{\"message\":\"$ackCode$\",\"messageEncoding\":\"UTF-8\",\"format\":\"wave\",\"altText\":\"$ackCode$\"},"
                        + "{\"message\":\"$ackCode$\",\"messageEncoding\":\"UTF-8\",\"format\":\"barcode\",\"altText\":\"$ackCode$\"}" + "],"
                        + "\"remindInfo\":{\"offset\":1},\"einfo\":{\"logoText\":\"$filmName$\",\"secondLogoText\":\"$secondLogoText$\",\"headFields\":[{\"key\":\"filmVersion\",\"value\":\"$filmVersion$\",\"label\":\"类型\",\"type\":\"text\"}],\"primaryFields\":[{\"key\":\"dayTime\",\"value\":\"$dayTime$\",\"label\":\"日期\",\"type\":\"text\"},{\"key\":\"hourTime\",\"value\":\"$hourTime$\",\"label\":\"时间\",\"type\":\"text\"}],\"secondaryFields\":[{\"key\":\"cinemaName\",\"value\":\"$cinemaName$\",\"label\":\"影院\",\"type\":\"text\"}],\"auxiliaryFields\":[{\"key\":\"hallName\",\"value\":\"$hallName$\",\"label\":\"影厅\",\"type\":\"text\"},{\"key\":\"count\",\"value\":\"$count$\",\"label\":\"张数\",\"type\":\"text\"},{\"key\":\"seatsInfo\",\"value\":\"$seatsInfo$\",\"label\":\"座位\",\"type\":\"text\"}],\"backFields\":[{\"key\":\"introduction\",\"value\":\"1. 选座票由时光代理，客服热线:4006-118-118\\n2. 电影票属特殊物品，出票成功后将不予退换，请按照场次时间使用\\n3. 电影票使用时，需去对应影院的时光网取票机或影院服务台进行取票\",\"label\":\"免责声明\",\"type\":\"text\"}]},\"locations\":[{\"addr\":\"$addr$\",\"tel\":\"$tel$\",\"relevantText\":\"$relevantText$\",\"longitude\":\"$longitude$\",\"latitude\":\"$latitude$\"}]},\"style\":{\"backgroundColor\":\"RGB(26,150,219)\"},\"fileInfo\":{\"canShare\":false,\"formatVersion\":\"2\",\"serialNumber\":\"$serialNumber$\"},\"merchant\":{\"mname\":\"Alipassprod\",\"mtel\":\"\",\"minfo\":\"\"},\"platform\":{\"channelID\":\"$channelID$\",\"webServiceUrl\":\"$webServiceUrl$\"},\"appInfo\":null,\"alipayVerify\":[]}";

        String logo = "https://tfsimg.alipay.com/images/kabaoprod/T1uoldXeVlXXaCwpjX";

        // 组装接口入参参数
        TemplateCreateRequest templateReq = new TemplateCreateRequest();
        templateReq.setAppAuthToken(appAuthToken);

        templateReq.setLogo(logo);
        templateReq.setContent(content);
        templateReq.setUniqueId(String.valueOf(System.currentTimeMillis()));// 外部唯一标识

        // 调用请求
        AlipayPassTemplateAddResponse response = transferService.createTemplate(templateReq);

        // 解析templateId
        String templateId = null;
        if (response.isSuccess()) {
            templateId = (String) JSON.readValue(response.getResult(), Map.class).get("tpl_id");
            // TODO 具体业务处理
        } else {
            // TODO 异常业务处理
        }
        System.out.println(
                "新增卡券模板结果：success=" + response.isSuccess() + "\r\n,result=" + response.getResult() + "\r\n,subCode=" + response.getSubCode()
                        + "\r\n,subMsg=" + response.getSubMsg());
        return templateId;
    }

    /**
     * Alipass模版更新接口测试
     *
     * @throws AlipayApiException
     */
    private void modifyTemplate(String templateId) throws Exception {

        String content =
                "{\"evoucherInfo\":{\"title\":\"$filmName$\",\"startDate\":\"$startDate$\",\"endDate\":\"$endDate$\",\"type\":\"eventTicket\",\"product\":\"groupon\","
                        + "\"operation\":["
                        + "{\"message\":\"$ackCode$\",\"messageEncoding\":\"UTF-8\",\"format\":\"wave\",\"altText\":\"$ackCode$\"},"
                        + "{\"message\":\"$ackCode$\",\"messageEncoding\":\"UTF-8\",\"format\":\"barcode\",\"altText\":\"$ackCode$\"}" + "],"
                        + "\"remindInfo\":{\"offset\":1},\"einfo\":{\"logoText\":\"$filmName$\",\"secondLogoText\":\"$secondLogoText$\",\"headFields\":[{\"key\":\"filmVersion\",\"value\":\"$filmVersion$\",\"label\":\"类型\",\"type\":\"text\"}],\"primaryFields\":[{\"key\":\"dayTime\",\"value\":\"$dayTime$\",\"label\":\"日期\",\"type\":\"text\"},{\"key\":\"hourTime\",\"value\":\"$hourTime$\",\"label\":\"时间\",\"type\":\"text\"}],\"secondaryFields\":[{\"key\":\"cinemaName\",\"value\":\"$cinemaName$\",\"label\":\"影院\",\"type\":\"text\"}],\"auxiliaryFields\":[{\"key\":\"hallName\",\"value\":\"$hallName$\",\"label\":\"影厅\",\"type\":\"text\"},{\"key\":\"count\",\"value\":\"$count$\",\"label\":\"张数\",\"type\":\"text\"},{\"key\":\"seatsInfo\",\"value\":\"$seatsInfo$\",\"label\":\"座位\",\"type\":\"text\"}],\"backFields\":[{\"key\":\"introduction\",\"value\":\"1. 选座票由时光代理，客服热线:4006-118-118\\n2. 电影票属特殊物品，出票成功后将不予退换，请按照场次时间使用\\n3. 电影票使用时，需去对应影院的时光网取票机或影院服务台进行取票\",\"label\":\"免责声明\",\"type\":\"text\"}]},\"locations\":[{\"addr\":\"$addr$\",\"tel\":\"$tel$\",\"relevantText\":\"$relevantText$\",\"longitude\":\"$longitude$\",\"latitude\":\"$latitude$\"}]},\"style\":{\"backgroundColor\":\"RGB(26,150,219)\"},\"fileInfo\":{\"canShare\":false,\"formatVersion\":\"2\",\"serialNumber\":\"$serialNumber$\"},\"merchant\":{\"mname\":\"Alipassprod\",\"mtel\":\"\",\"minfo\":\"\"},\"platform\":{\"channelID\":\"$channelID$\",\"webServiceUrl\":\"$webServiceUrl$\"},\"appInfo\":null,\"alipayVerify\":[]}";
        String logo = "https://tfsimg.alipay.com/images/kabaoprod/T16qXeXXBlXXaCwpjX";

        TemplateModifyRequest templateReq = new TemplateModifyRequest();
        templateReq.setAppAuthToken(appAuthToken);
        templateReq.setContent(content);
        templateReq.setLogo(logo);
        templateReq.setTemplateId(templateId);

        AlipayPassTemplateUpdateResponse res = transferService.modifyTemplate(templateReq);
        if (res.isSuccess()) {
            // TODO 具体业务处理
        } else {
            // TODO 异常业务处理
        }
        System.out.println(
                "修改卡券模板结果：success=" + res.isSuccess() + ",result=" + res.getResult() + ",subCode=" + res.getSubCode() + ",subMsg=" + res.getSubMsg());
    }

    /**
     * 添加卡券实例
     *
     * @param templateId
     * @throws Exception
     */
    private void addPassInstance(String templateId) throws Exception {
        //TODO: 注意：alipass的唯一标识，商户请使用外部交易号作为serialNumber
        String serialNumber = generateNumber(16);
        System.out.println(serialNumber);

        Map<String, String> paramValuePair = new HashMap<String, String>();
        paramValuePair.put("status", "可使用");
        paramValuePair.put("providerPhone", "15663461691");
        paramValuePair.put("serialNumber", serialNumber);
        paramValuePair.put("channelID", AlipassSDKConstants.APP_ID);


        Map<String, String> userParams = new HashMap<String, String>();
        userParams.put(BaseAddRequest.PARTNER_ID, AlipassSDKConstants.PID);
        //TODO:填写外部交易号
        userParams.put(BaseAddRequest.OUT_TRADE_NO, "201603036737");


        AddPassInstanceRequest addReq = new AddPassInstanceRequest();
        addReq.setAppAuthToken(appAuthToken);
        addReq.setTemplateId(templateId);
        addReq.setTemplateParamValuePair(paramValuePair);
        addReq.setUserType(RecognitionTypeEnum.TRADE);
        addReq.setUserTypeParams(userParams);


        AlipayPassInstanceAddResponse res = transferService.addPassInstance(addReq);
        if (res.isSuccess()) {
            // TODO 具体业务处理
        } else {
            // TODO 异常业务处理
        }
        System.out.println(
                "新增卡券实例结果：success=" + res.isSuccess() + ",result=" + res.getResult() + ",subCode=" + res.getSubCode() + ",subMsg=" + res.getSubMsg());

    }

    /**
     * 更新卡券实例
     *
     * @param templateId
     * @throws Exception
     */
    private void updatePassInstance(String templateId) throws Exception {
        //TODO:注意：alipass的唯一标识，商户请使用外部交易号作为serialNumber。更新卡券实例，必填字段
        String serialNumber = "4534744160077235";

        UpdatePassInstanceRequest request = new UpdatePassInstanceRequest();
        request.setAppAuthToken(appAuthToken);

        request.setSerialNumber(serialNumber);
        request.setChannelId(AlipassSDKConstants.APP_ID);
        Map<String, String> templateParamValuePair = new HashMap<String, String>();
        templateParamValuePair.put("filmVersion", "5D");
        request.setTemplateParamValuePair(templateParamValuePair);
        request.setStatus(PassStatus.PASS_STATUS_USED);
        request.setVerifyType("wave");
        request.setVerifyCode("231311");
        AlipayPassInstanceUpdateResponse res = transferService.updatePassInstance(request);
        if (res.isSuccess()) {
            // TODO 具体业务处理
        } else {
            // TODO 异常业务处理
        }
        System.out.println(
                "修改卡券实例结果：success=" + res.isSuccess() + ",result=" + res.getResult() + ",subCode=" + res.getSubCode() + ",subMsg=" + res.getSubMsg());
    }

    /**
     * 生成随机数字，用于生成随机Serialnumber
     *
     * @param codeLength
     * @return
     */
    public static String generateNumber(int codeLength) {
        // 10个数字
        final int maxNum = 8;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < codeLength) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

}
