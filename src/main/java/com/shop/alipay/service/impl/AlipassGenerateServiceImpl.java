/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.service.impl;



import com.shop.alipay.enums.Constants;
import com.shop.alipay.enums.FileName;
import com.shop.alipay.enums.PicName;
import com.shop.alipay.enums.Result;
import com.shop.alipay.model.AlipassModel;
import com.shop.alipay.model.ResponseModel;
import com.shop.alipay.service.AlipassGenerateService;
import com.shop.alipay.utils.*;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * AlipassGenerateService接口的实现类，接收参数并返回alipass文件
 * @author siyu.jsy
 * @version $Id: AlipassGenerateServiceImpl.java,v 0.1 2013-5-3 下午1:35:19 siyu.jsy Exp $
 */
public class AlipassGenerateServiceImpl implements AlipassGenerateService {

    /**
     * 接收AlipassModel对象，校验并根据生成规则生成alipass文件。
     * @param alipassModel
     * @return
     */
    public ResponseModel alipassGenerate(AlipassModel alipassModel, String privateKey,
                                         HashMap<PicName, byte[]> picMap, Object... objects) {

        //校验请求数据
        ResponseModel responseModel = ValidateUtils.alipassRequestValidate(alipassModel,
            privateKey, picMap, objects);
        Result alipassresult = responseModel.getResult();

        //取出存放临时文件的目录
        String tempPath = getTempFilePath(alipassresult, alipassModel.getFileInfo()
            .getSerialNumber());

        // 设置不同的source
        if (alipassresult.equals(Result.SUCCESS)) {
            // Alipass Source调用
            if (objects != null
                && objects.length == Integer.parseInt(Constants.ALIPASS_SOURCE_PARAMS_COUNT
                    .getValue())) {
                alipassModel.setSource((String) objects[0]);
            }
        }
        // 组装并生成pass.json文件
        if (alipassresult.equals(Result.SUCCESS)) {
            alipassresult = FileGenerateUtils.passJsonFileCreate(tempPath, alipassModel,
                alipassresult);
        }

        // 签名并生成签名文件
        TreeMap<String, File> fileMap = null;
        if (alipassresult.equals(Result.SUCCESS)) {
            fileMap = passFilesCollection(tempPath, alipassModel, picMap);
            alipassresult = FileGenerateUtils.signFileGenerate(tempPath, alipassModel, fileMap,
                alipassresult, privateKey);
        }

        String base64AlipassFile = null;
        // 打包生成alipass文件
        if (alipassresult.equals(Result.SUCCESS) && fileMap != null) {
            String alipassFilePath = FileGenerateUtils.alipassFilegenerate(tempPath, alipassModel,
                fileMap);
            base64AlipassFile = FileGenerateUtils.base64AlipassGenerate(tempPath, alipassFilePath,
                alipassresult);
        }

        // 如果文件超过512K字节
        if (ExtendStringUtil.length(base64AlipassFile) > 524288) {
            alipassresult = Result.ALIPASS_FILE_TOO_LARGE;
            base64AlipassFile = null;
        }

        // 删除临时文件及目录
        FileUtils.delete(tempPath);

        // 构造返回数据
        ResponseModel response = new ResponseModel();
        if (alipassresult.equals(Result.SUCCESS)) {
            response.setResult(alipassresult);
            response.setAlipass(base64AlipassFile);
        } else {
            response.setResult(alipassresult);
        }

        // 返回alipass文件对应的流 及结果
        return response;
    }

    /**
     * 将参与alipass生成的文件都放到map中,接收Alipass Source传递的图片字节数组
     * @param tempPath
     * @param alipassModel
     * @param picMapFromSource
     * @return
     */
    private TreeMap<String, File> passFilesCollection(String tempPath,
                                                               AlipassModel alipassModel,
                                                               HashMap<PicName, byte[]> picMapFromSource) {

        TreeMap<String, File> fileMap = new TreeMap<String, File>();

        // 从请求参数中获取图片文件,并改名
        byte[] logoFile = picMapFromSource.get(PicName.logo);
        File newLogoFile = null;
        boolean logoCopyFlag = FileUtils.copy(logoFile, tempPath + PicName.logo.getPicName());
        if (logoCopyFlag) {
            newLogoFile = new File(tempPath + PicName.logo.getPicName());
        }
        if (newLogoFile != null) {
            fileMap.put(PicName.logo.getPicName(), newLogoFile);
        }

        // 如果存在Strip文件，获取之
        byte[] stripFile = null;
        File newStripFile = null;
        if (picMapFromSource.containsKey(PicName.strip)) {
            stripFile = picMapFromSource.get(PicName.strip);
            boolean copyStripFlag = FileUtils
                .copy(stripFile, tempPath + PicName.strip.getPicName());
            if (copyStripFlag) {
                newStripFile = new File(tempPath + PicName.strip.getPicName());
            }
            if (newStripFile != null) {
                fileMap.put(PicName.strip.getPicName(), newStripFile);
            }
        }

        // 如果存在icon文件，获取之
        byte[] iconFile = null;
        File newIconFile = null;
        if (picMapFromSource.containsKey(PicName.icon)) {
            iconFile = picMapFromSource.get(PicName.icon);
            boolean copyIconFlag = FileUtils.copy(iconFile, tempPath + PicName.icon.getPicName());
            if (copyIconFlag) {
                newIconFile = new File(tempPath + PicName.icon.getPicName());
            }
            if (newIconFile != null) {
                fileMap.put(PicName.icon.getPicName(), newIconFile);
            }
        }

        // 读取生成的pass.json文件
        File passJsonFile = new File(tempPath + FileName.pass.getFileName());
        fileMap.put(FileName.pass.getFileName(), passJsonFile);

        return fileMap;
    }

    /**
     * 获取临时文件存储目录
     * @param alipassresult 
     * @param serialNumber
     * @return
     */
    private String getTempFilePath(Result alipassresult, String serialNumber) {
        String tempPath = serialNumber + "/";

        // 如果目录不存在，创建临时目录
        if (StringUtil.isNotBlank(tempPath)) {
            File tempPathFile = new File(tempPath);
            if (!tempPathFile.exists()) {
                tempPathFile.mkdirs();
            }
        }

        return tempPath;
    }
}
