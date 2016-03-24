/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * 编码格式的枚举类型
 * @author siyu.jsy
 * @version $Id: CharSet.java,v 0.1 2013-5-5 下午8:33:04 siyu.jsy Exp $
 */
public enum Constants {

    /**编码*/
    UTF8("UTF-8"),

    GBK("GBK"),

    ISO_8859_1("ISO-8859-1"),

    /** RSA签名 算法*/
    SIGNATURE_ALGORITHM("SHA1WithRSA"),

    /**RSA*/
    KEY_ALGORITHM("RSA"),

    /**时间格式*/
    TIME_FORMAT("yyyy-MM-dd HH:mm:ss"),

    /**配置文件中私钥的key*/
    PRI_KEY("private_key"),

    /**平台回掉地址URL*/
    PLT_WEBSERVICE_URL("platform_webservice_url"),

    /**支付宝合作ID*/
    PARTNER_ID("partner_Id"),

    /**alipass新增接口*/
    CREATE_INTERFACE("alipay.mobile.alipass.create"),

    /**alipass更新接口*/
    UPDATE_INTERFACE("alipay.mobile.alipass.data.sync"),

    /**生成Alipass时生成的临时文件存放目录*/
    TEMP_FILE_PATH("temp_file_path"),

    /**
     * 以下数据用于替换json中固定字符串
     */
    TEXTMESSAGE("textMessage"),

    APPMESSAGE("appMessage"),

    IMGMESSAGE("imgMessage"),

    MESSAGE("message"),

    /**
     * Alipass Source调用时，需要设置可选项参数 ,根据约定参数的数目，目前为1
     */
    ALIPASS_SOURCE_PARAMS_COUNT("1"),

    /**
     * Json节点名称
     */
    JSONNODE_EVOUCHERINFO("evoucherInfo"),

    JSONNODE_OPERATION("operation"),

    JSONNODE_LOCATIONS("locations"),

    JSONNODE_FORMAT("format"),

    /**
     * format节点数据
     */
    JSONNODE_FORMAT_VALUE_APP("\"app\""),

    JSONNODE_FORMAT_VALUE_TEXT("\"text\""),

    JSONNODE_FORMAT_VALUE_IMG("\"img\""),

    /**
     * Format的key、value字符串
     */
    JSONNODE_FORMAT_KEY_VALUE_APP("\"format\":\"app\""),

    JSONNODE_FORMAT_KEY_VALUE_TEXT("\"format\":\"text\""),

    JSONNODE_FORMAT_KEY_VALUE_IMG("\"format\":\"img\""),

    /**
     * message节点数据
     */
    JSONNODE_MESSAGE_VALUE_MESSAGE("\"message\""),

    JSONNODE_MESSAGE_VALUE_APPMESSAGE("\"appMessage\""),

    JSONNODE_MESSAGE_VALUE_TEXTMESSAGE("\"textMessage\""),

    JSONNODE_MESSAGE_VALUE_IMGMESSAGE("\"imgMessage\""),

    /**
     * Operation节点数据
     */
    JSONNODE_OPERATION_VALUE("\"operation\":"),

    /**
     * pass状态
     */
    PASS_STATUS_CLOSED("CLOSED"),

    PASS_STATUS_USED("USED");

    private String value;

    public String getValue() {
        return value;
    }

    private Constants(String charset) {
        this.value = charset;
    }

    private Constants() {
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static Constants get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (Constants keyType : Constants.values()) {
            if (keyType.getValue().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
