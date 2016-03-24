/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;


import com.shop.alipay.enums.OperationFormatType;
import com.shop.alipay.enums.PicName;
import com.shop.alipay.enums.Result;
import com.shop.alipay.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ValidateUtils {

    /**
     * 校验请求数据
     * 校验规则如下：
     * 1、logo.png必传
     * 2、参数是否必传，具体参照接口文档
     * 3、参数长度判断，具体参照接口文档
     * @param alipassModel 请求数据
     * @param privateKey 商户私钥
     * @param picMap 图片数据
     * @param objects 扩展属性
     */
    public static ResponseModel alipassRequestValidate(AlipassModel alipassModel,
                                                       String privateKey,
                                                       HashMap<PicName, byte[]> picMap,
                                                       Object... objects) {

        Result validateResult = Result.SUCCESS;

        if (alipassModel == null) {
            validateResult = Result.REQDATA_NOT_EXIST;
        }

        // logo.png 是否存在
        if (validateResult.equals(Result.SUCCESS)) {
            validateResult = picFileValidate(picMap, PicName.logo, validateResult);
        }

        // 一级必需属性是否存在
        if (validateResult.equals(Result.SUCCESS)
            && (alipassModel.getEvoucherInfo() == null || alipassModel.getMerchant() == null || alipassModel
                .getFileInfo() == null && StringUtil.isBlank(privateKey))) {
            validateResult = Result.ESSENTIAL_FIELDS_NOT_EXIST;
        }

        // EvoucherInfo中的必需属性是否存在 及部分数据的长度检查
        if (validateResult.equals(Result.SUCCESS)) {
            ResponseModel evoucherValidateRes = evoucherInfoValidate(alipassModel, validateResult);
            if (Result.SUCCESS.equals(evoucherValidateRes.getResult())) {
                alipassModel = evoucherValidateRes.getAlipassModel();
            } else {
                validateResult = evoucherValidateRes.getResult();
            }
        }

        // merchant中的必需属性是否存在
        if (validateResult.equals(Result.SUCCESS)) {
            validateResult = merchantValidate(alipassModel.getMerchant(), validateResult);
        }

        // 从配置文件中获取Platform必需属性
        if (validateResult.equals(Result.SUCCESS)) {
            validateResult = platformValidate(alipassModel.getPlatform(), validateResult);
        }

        // FileInfo必需属性是否存在 及部分数据的长度检查
        if (validateResult.equals(Result.SUCCESS)) {
            validateResult = fileInfoValidate(alipassModel.getFileInfo(), validateResult);
        }

        ResponseModel responseModel = new ResponseModel();
        if (Result.SUCCESS.equals(validateResult)) {
            responseModel.setResult(validateResult);
            responseModel.setAlipassModel(alipassModel);
        } else {
            responseModel.setResult(validateResult);
        }

        return responseModel;
    }

    /**
     * 文件属性中必需字段是否存在 及部分数据的长度检查
     * @param fileInfo
     * @param validateResult
     * @return
     */
    private static Result fileInfoValidate(FileInfoModel fileInfo, Result validateResult) {

        //存在性判断
        if (StringUtil.isBlank(fileInfo.getFormatVersion())
            || StringUtil.isBlank(fileInfo.getSerialNumber())) {

            validateResult = Result.FILE_ESSENTIAL_FIELDS_NOT_EXIST;
        }

        // 长度判断
        if (validateResult.equals(Result.SUCCESS)
            && ExtendStringUtil.length(fileInfo.getSerialNumber()) > 32) {
            validateResult = Result.FILE_FIELDS_LEN_ERR;
        }

        return validateResult;
    }

    /**
     * 渠道必需属性是否存在 及部分数据的长度检查
     * @param platform
     * @param validateResult
     * @return
     */
    private static Result platformValidate(PlatformModel platform, Result validateResult) {

        //TODO channelType, default openapi

        if (StringUtil.isBlank(platform.getChannelID())) {
            validateResult = Result.PTF_ESSENTIAL_FIELDS_NOT_EXIST;
        }
        // 长度判断
        if (validateResult.equals(Result.SUCCESS)
            && (ExtendStringUtil.length(platform.getChannelID()) > 32 || ExtendStringUtil
                .length(platform.getWebServiceUrl()) > 128)) {
            validateResult = Result.PTF_FIELDS_LEN_ERR;
        }

        return validateResult;
    }

    /**
     * 判断商戶中必传值是否存在
     * @param merchant
     * @param validateResult 
     * @return
     */
    private static Result merchantValidate(MerchantModel merchant, Result validateResult) {

        if (StringUtil.isBlank(merchant.getMerName())) {
            validateResult = Result.MER_ESSENTIAL_FIELDS_NOT_EXIST;
        }

        return validateResult;
    }

    /**
     * 判断给定的图片文件 在 map中是否存在
     * @param picMap
     * @param validateResult 
     * @return
     */
    private static Result picFileValidate(HashMap<PicName, byte[]> picMap, PicName picName,
                                          Result validateResult) {

        // Merchant SDK
        if ((!picMap.containsKey(picName) || picMap.get(picName).length == 0)) {
            validateResult = Result.LOGO_NOT_EXIST;
        }
        return validateResult;
    }

    /**
     * 判断基础属性中必传值是否有值 及部分数据的长度检查
     * @param alipassModel
     * @param validateResult 
     * @return
     */
    private static ResponseModel evoucherInfoValidate(AlipassModel alipassModel,
                                                      Result validateResult) {

        EVoucherInfoModel evoucherInfo = alipassModel.getEvoucherInfo();

        // 存在性判断
        if (StringUtil.isBlank(evoucherInfo.getTitle())
            || StringUtil.isBlank(evoucherInfo.getStartDate())
            || StringUtil.isBlank(evoucherInfo.getEndDate()) || evoucherInfo.getType() == null
            || evoucherInfo.getProduct() == null || evoucherInfo.getEinfo() == null) {
            validateResult = Result.EVI_ESSENTIAL_FIELDS_NOT_EXIST;
        }

        // 数据长度判断
        if (validateResult.equals(Result.SUCCESS)) {
            if ((StringUtil.isNotBlank(evoucherInfo.getGoodsId()) && ExtendStringUtil
                .length(evoucherInfo.getGoodsId()) > 64)
                || ExtendStringUtil.length(evoucherInfo.getStartDate()) > 19
                || ExtendStringUtil.length(evoucherInfo.getEndDate()) > 19) {

                validateResult = Result.EVI_FIELDS_LEN_ERR;
            }
        }

        // 时间格式判断
        if (validateResult.equals(Result.SUCCESS)) {
            if (!((evoucherInfo.getStartDate().startsWith("$") && evoucherInfo.getStartDate()
                .endsWith("$")) || TimeUtils.isDateStringValid(evoucherInfo.getStartDate()))) {
                validateResult = Result.TIME_FORMAT_ERR;
            }
        }

        // 时间格式判断
        if (validateResult.equals(Result.SUCCESS)) {
            if (!((evoucherInfo.getEndDate().startsWith("$") && evoucherInfo.getEndDate().endsWith(
                "$")) || TimeUtils.isDateStringValid(evoucherInfo.getEndDate()))) {
                validateResult = Result.TIME_FORMAT_ERR;
            }
        }

        // EInfo数据判断
        if (validateResult.equals(Result.SUCCESS)) {
            validateResult = einfoValidate(evoucherInfo, validateResult);
        }

        // 操作信息判断
        if (validateResult.equals(Result.SUCCESS) && evoucherInfo.getOperation() != null
            && evoucherInfo.getOperation().size() > 0) {
            ResponseModel messageRes = operationValidate(alipassModel, validateResult);
            if (Result.SUCCESS.equals(messageRes.getResult())) {
                alipassModel = messageRes.getAlipassModel();
            } else {
                validateResult = messageRes.getResult();
            }
        }

        // 封装返回数据
        ResponseModel responseModel = new ResponseModel();
        if (validateResult.equals(Result.SUCCESS)) {
            responseModel.setResult(validateResult);
            responseModel.setAlipassModel(alipassModel);
        } else {
            responseModel.setResult(validateResult);
        }

        return responseModel;
    }

    /**
     * 校验Operation数据
     * @param alipassModel
     * @param validateResult
     * @return
     */
    private static ResponseModel operationValidate(AlipassModel alipassModel, Result validateResult) {

        EVoucherInfoModel evoucherInfo = alipassModel.getEvoucherInfo();

        List<OperationModel> operations = evoucherInfo.getOperation();
        List<OperationModel> newOperations = new ArrayList<OperationModel>();

        for (OperationModel operationModel : operations) {

            // app operation
            if (StringUtil.equals(operationModel.getOpFormat(),
                OperationFormatType.app.getTypeName())) {
                if (operationModel.getAppMessage() == null) {
                    validateResult = Result.OPERATION_MESSAGE_FORMAT_NOT_MATCH;
                } else {
                    operationModel.setOpMessage(null);
                    operationModel.setTextMessage(null);
                    operationModel.setImgMessage(null);
                    newOperations.add(operationModel);
                }
            }
            // text operation
            else if (StringUtil.equals(operationModel.getOpFormat(),
                OperationFormatType.text.getTypeName())) {
                if (operationModel.getTextMessage() == null) {
                    validateResult = Result.OPERATION_MESSAGE_FORMAT_NOT_MATCH;
                } else {
                    operationModel.setAppMessage(null);
                    operationModel.setOpMessage(null);
                    operationModel.setImgMessage(null);
                    newOperations.add(operationModel);
                }
            }
            // img operation
            else if (StringUtil.equals(operationModel.getOpFormat(),
                OperationFormatType.img.getTypeName())) {
                if (operationModel.getImgMessage() == null) {
                    validateResult = Result.OPERATION_MESSAGE_FORMAT_NOT_MATCH;
                } else {
                    operationModel.setAppMessage(null);
                    operationModel.setTextMessage(null);
                    operationModel.setOpMessage(null);
                    newOperations.add(operationModel);
                }

            }
            // others operation
            else {
                if (operationModel.getOpMessage() == null) {
                    validateResult = Result.OPERATION_MESSAGE_FORMAT_NOT_MATCH;
                } else {
                    operationModel.setAppMessage(null);
                    operationModel.setTextMessage(null);
                    operationModel.setImgMessage(null);
                    newOperations.add(operationModel);
                }
            }

        }

        ResponseModel responseModel = new ResponseModel();
        if (validateResult.equals(Result.SUCCESS)) {
            evoucherInfo.setOperation(newOperations);
            alipassModel.setEvoucherInfo(evoucherInfo);

            responseModel.setResult(validateResult);
            responseModel.setAlipassModel(alipassModel);
        } else {
            responseModel.setResult(validateResult);
        }

        return responseModel;
    }

    /**
     * 判断EInfo中数据内容
     * @param evoucherInfo
     * @param validateResult
     * @return
     */
    private static Result einfoValidate(EVoucherInfoModel evoucherInfo, Result validateResult) {

        // 判断logoText 是否存在
        if (StringUtil.isBlank(evoucherInfo.getEinfo().getLogoText())) {
            validateResult = Result.ESSENTIAL_FIELDS_NOT_EXIST;
        }

        if (validateResult.equals(Result.SUCCESS)) {
            // 将存在数据的区域放入list
            List<EInfoUnitModel> tempList = fillUpEinfoList(evoucherInfo.getEinfo());

            // 循环检查EInfoUnit中必需值是否存在
            if (!CollectionUtils.isEmpty(tempList)) {
                // 获取单个EInfoUnitModel对象
                for (EInfoUnitModel eInfoUnitModel : tempList) {
                    // 验证EInfoUnitModel中的必需值是否存在
                    validateResult = einfoUnitValidate(eInfoUnitModel, validateResult);
                    // 某一个不存在 直接break
                    if (!validateResult.equals(Result.SUCCESS)) {
                        break;
                    }
                }
            } else {
                validateResult = Result.EINFO_ESSENTIAL_FIELDS_NOT_EXIST;
            }
        }

        return validateResult;
    }

    /**
     * 判断EInfoUnit中的必需字段是否存在
     * @param eInfoUnitModel
     * @param validateResult
     * @return
     */
    private static Result einfoUnitValidate(EInfoUnitModel eInfoUnitModel, Result validateResult) {

        if (StringUtil.isBlank(eInfoUnitModel.getKey())
            || (StringUtil.isBlank(eInfoUnitModel.getLabel()) && StringUtil.isBlank(eInfoUnitModel
                .getValue()))) {

            validateResult = Result.EINFO_ESSENTIAL_FIELDS_NOT_EXIST;
        }

        return validateResult;
    }

    /**
     * 將存在值的EinfoUnit填充到本列表
     * @param einfo
     * @return
     */
    private static List<EInfoUnitModel> fillUpEinfoList(EInfoModel einfo) {

        List<EInfoUnitModel> einfoList = new ArrayList<EInfoUnitModel>();

        // HeadFields
        if (!CollectionUtils.isEmpty(einfo.getHeadFields())) {
            einfoList.addAll(einfo.getHeadFields());
        }

        // PrimaryFields
        if (!CollectionUtils.isEmpty(einfo.getPrimaryFields())) {
            einfoList.addAll(einfo.getPrimaryFields());
        }

        // SecondaryFields
        if (!CollectionUtils.isEmpty(einfo.getSecondaryFields())) {
            einfoList.addAll(einfo.getSecondaryFields());
        }

        // AuxiliaryFields
        if (!CollectionUtils.isEmpty(einfo.getAuxiliaryFields())) {
            einfoList.addAll(einfo.getAuxiliaryFields());
        }

        // BackFields
        if (!CollectionUtils.isEmpty(einfo.getBackFields())) {
            einfoList.addAll(einfo.getBackFields());
        }
        return einfoList;
    }
}
