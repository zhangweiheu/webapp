/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */

package com.shop.alipay.enums;


import com.shop.alipay.utils.StringUtil;

/**
 * 操作区支持的功能类型枚举
 * @author siyu.jsy
 * @version $Id: OperationFormatType.java,v 0.1 2013-6-9 下午9:17:09 siyu.jsy Exp $
 */
public enum OperationFormatType {

    /**App类型Message*/
    app("app"),

    /**Text类型Message*/
    text("text"),

    /**条形码类型Message*/
    barcode("barcode"),

    /**二维码类型Message**/
    qrcode("qrcode"),

    /**声波类型Message**/
    wave("wave"),

    /**图片类型Message*/
    img("img"),

    /** http链接类型Message*/
    url("url"),

    /** 动态条码类型Message*/
    dbarcode("dbarcode"),

    /** 动态二维码类型Message*/
    dqrcode("dqrcode"),

    /** 动态声波类型Message*/
    dwave("dwave"), ;

    private String typeName;

    private OperationFormatType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 通过value获取枚举
     * 
     * @param value  大类枚举编码
     * @return  大类枚举
     */
    public static OperationFormatType get(String value) {
        if (StringUtil.isBlank(value)) {
            return null;
        }
        for (OperationFormatType keyType : OperationFormatType.values()) {
            if (keyType.getTypeName().equals(value)) {
                return keyType;
            }
        }
        return null;
    }

}
