/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * 生成Alipass时的结果集
 * @author siyu.jsy
 * @version $Id: Result.java,v 0.1 2013-5-3 下午3:16:01 siyu.jsy Exp $
 */
public enum Result {

    /**成功*/
    SUCCESS("Success", "成功"),

    /**未知错误*/
    UNKNOWN_ERROR("Unknown error", "未知错误"),

    /**Logo.png不存在*/
    LOGO_NOT_EXIST("The logo.png file does not exist", "logo图片不存在"),

    /**请求数据不存在*/
    REQDATA_NOT_EXIST("Request data do not exist", "请求数据不存在"),

    /**必传属性不存在*/
    ESSENTIAL_FIELDS_NOT_EXIST("Essential fields do not exist", "必需属性值缺失"),

    /**基础属性中必传属性不存在*/
    EVI_ESSENTIAL_FIELDS_NOT_EXIST("EVoucherInfo essential fields do not exist", "基础属性中必需值缺失"),

    /**商户属性中必传属性不存在*/
    MER_ESSENTIAL_FIELDS_NOT_EXIST("Merchant essential fields do not exist", "商戶属性中必需值缺失"),

    /**渠道属性中必传属性不存在*/
    PTF_ESSENTIAL_FIELDS_NOT_EXIST("Platform essential fields do not exist", "渠道属性中必需值缺失"),

    /**文件属性中必传属性不存在*/
    FILE_ESSENTIAL_FIELDS_NOT_EXIST("File info essential fields do not exist", "文件属性中必需值缺失"),

    EINFO_ESSENTIAL_FIELDS_NOT_EXIST("EInfo essential fields do not exist", "EInfo属性中必需值缺失"),
    /**文件属性中字段长度错误*/
    FILE_FIELDS_LEN_ERR("File info fields' length error", "文件属性字段长度错误"),

    /**渠道属性中字段长度错误*/
    PTF_FIELDS_LEN_ERR("Platform fields' length error", "渠道属性字段长度错误"),

    /**基础属性中字段长度错误*/
    EVI_FIELDS_LEN_ERR("EvoucherInfo fields' length error", "基础属性字段长度错误"),

    /**Json转换错误*/
    JSON_TRANSFORM_ERROR("Json transform error", "Json数据转换错误"),

    /**配置文件不存在*/
    CONFIG_NOT_EXIST("Config file does not exist", "配置项不存在，请检查配置文件"),

    /**Alipass文件过大*/
    ALIPASS_FILE_TOO_LARGE("Alipass file is too large", "Alipass文件长度超出允许范围"),

    /**时间格式错误*/
    TIME_FORMAT_ERR("Time format error", "时间格式错误"),

    /**操作信息中*/
    OPERATION_MESSAGE_FORMAT_NOT_MATCH("Operation format and message do not match",
                                       "操作信息与类型不匹配，请检查"),

    /**提醒信息非法*/
    REMIND_INFO_ILLEGAL("Remind Info illegal", "提醒设置有误，请检查"),

    /** 参数非法*/
    ILLEGAL_PARAM("Illegal param", "不识别的参数，请检查"),

    FORMAT_ERROR("format error", "格式错误"), ;

    private String resultValue;

    private String resultMsg;

    private Result() {
    }

    private Result(String resultValue, String resultMsg) {
        this.resultValue = resultValue;
        this.resultMsg = resultMsg;
    }

    public String getResultValue() {
        return resultValue;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static Result get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (Result keyType : Result.values()) {
            if (keyType.getResultValue().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
