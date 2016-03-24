/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.service;



import com.shop.alipay.enums.PicName;
import com.shop.alipay.model.AlipassModel;
import com.shop.alipay.model.ResponseModel;

import java.util.HashMap;

/**
 * Alipass 生成接口
 * @author siyu.jsy
 * @version $Id: AlipassGenerateService.java,v 0.1 2013-5-3 下午1:20:56 siyu.jsy Exp $
 */
public interface AlipassGenerateService {

    /**
     * 传入请求参数，返回Alipass文件
     * @param alipassModel
     * @param privateKey,代表签名过程过程中需要的私钥
     * @param picMap,HashMap<PicName,byte[]>类型，用于Alipass Source调用时，将图片文件传递过来。
     *              key为如下值：
     *              PicName.logo ：表示logo文件
     *              PicName.strip　：表示strip文件
     *              PicName.icon　：表示icon文件
     * @param objects,可选参数，目前传递以下内容：
     * 1、source，用于标识alipass来源。Alipass source调用时，需要作为入参传递。商户调用时，不需要传递。
     * @return
     */
    public ResponseModel alipassGenerate(AlipassModel alipassModel, String privateKey, HashMap<PicName, byte[]> picMap, Object... objects);
}
