/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * 图片名称的枚举
 * @author siyu.jsy
 * @version $Id: PicName.java,v 0.1 2013-5-2 下午4:41:57 siyu.jsy Exp $
 */
public enum PicName {

    /**logo.png,渠道商图标*/
    logo("logo.png"),

    /**pass内容主题图片*/
    strip("strip.png"),

    /**icon图片*/
    icon("icon.png");

    /**图片名称属性*/
    private String picName;

    private PicName(String picName) {
        this.picName = picName;
    }

    public String getPicName() {
        return picName;
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static PicName get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (PicName keyType : PicName.values()) {
            if (keyType.getPicName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
