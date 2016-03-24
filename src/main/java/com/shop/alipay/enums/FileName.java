/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * 项目中文件的名称枚举
 * @author siyu.jsy
 * @version $Id: AlipassFileName.java,v 0.1 2013-5-4 下午12:32:12 siyu.jsy Exp $
 */
public enum FileName {

    /**pass.json文件*/
    pass("pass.json"),

    /**签名文件*/
    sign("signature"),

    /**Alipass config配置文件*/
    alipass_config_file("/alipass_sdk_config.properties");

    private String fileName;

    private FileName() {
    }

    private FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static FileName get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (FileName keyType : FileName.values()) {
            if (keyType.getFileName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
