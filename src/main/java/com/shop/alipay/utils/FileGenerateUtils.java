/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;


import com.shop.alipay.enums.FileName;
import com.shop.alipay.enums.Result;
import com.shop.alipay.model.AlipassModel;
import com.shop.alipay.codec.Base64;
import java.io.*;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class FileGenerateUtils {

    /**
     * 将alipass文件进行Base64编码
     * 
     * @param alipassFilePath
     * @param tempPath
     * @param alipassresult
     * @return
     */
    public static String base64AlipassGenerate(String tempPath, String alipassFilePath,
                                               Result alipassresult) {

        String base64AlipassFile = null;
        if (alipassresult.equals(Result.SUCCESS) && StringUtil.isNotBlank(alipassFilePath)) {

            // 将文件进行base64编码
            base64AlipassFile = Base64.encodeBase64String(FileUtils.fileToByte(alipassFilePath));
        }
        return base64AlipassFile;
    }

    /**
     * 生成Alipass文件
     * @param tempPath 
     * 
     * @param alipassModel
     * @param fileMap 
     * @return
     */
    public static String alipassFilegenerate(String tempPath, AlipassModel alipassModel,
                                             TreeMap<String, File> fileMap) {

        // 从fileMap中获取文件列表， 包括图片文件、json文件
        Collection<File> collection = fileMap.values();
        List<File> fileList = new ArrayList<File>();
        Iterator<File> iterator = collection.iterator();
        while (iterator.hasNext()) {
            fileList.add(iterator.next());
        }

        //　将签名文件放入文件列表
        File signFile = new File(tempPath + FileName.sign.getFileName());
        fileList.add(signFile);

        // 生成Alipass文件，并保存
        String alipassFilePath = createPassFile(tempPath, fileList, alipassModel);

        // 返回Alipass文件路径
        return alipassFilePath;
    }

    /**
     * 生成签名文件
     * @param tempPath 
     * @param alipassModel
     * @param fileMap 
     * @param alipassresult
     * @param objects 
     * @return
     */
    public static Result signFileGenerate(String tempPath, AlipassModel alipassModel,
                                          TreeMap<String, File> fileMap, Result alipassresult,
                                          String privateKey) {

        // 使用treeMap保证顺序,生成每个文件的SHA摘要
        TreeMap<String, String> shaMap = signSrcMapGenerate(fileMap);

        String signSrc = null;
        //生成签名数据
        try {
            signSrc = JsonUtils.toJson(shaMap);
        } catch (IOException e) {
            alipassresult = Result.JSON_TRANSFORM_ERROR;
        }

        // 签名并保存到文件中
        if (alipassresult.equals(Result.SUCCESS) && StringUtil.isNotBlank(signSrc)) {
            alipassresult = signatureFileGenerate(tempPath, alipassModel, alipassresult, signSrc,
                privateKey);
        }
        return alipassresult;
    }

    /**
     * 获取私钥，签名并生成signature文件
     * @param tempPath 
     * @param alipassModel
     * @param alipassresult
     * @param signSrc 
     * @param objects 
     * @return
     */
    private static Result signatureFileGenerate(String tempPath, AlipassModel alipassModel,
                                                Result alipassresult, String signSrc,
                                                String privateKey) {

        if (alipassresult.equals(Result.SUCCESS)) {
            // 签名：
            String signData = Base64.encodeBase64String(SignatureUtils.sign(signSrc, privateKey));
            //生成pass.json文件，在完成alipass文件的生成后，需要删掉
            if (StringUtil.isNotBlank(signData)) {
                FileUtils.saveFile(tempPath + FileName.sign.getFileName(), signData);
            }
        }

        return alipassresult;
    }

    /**
     * 将alipass所需文件SHA按照文件名升序排序后返回
     * @param fileMap
     * @return
     */
    private static TreeMap<String, String> signSrcMapGenerate(TreeMap<String, File> fileMap) {
        // 使用treemap自动排序
        TreeMap<String, String> shaMap = new TreeMap<String, String>();

        Set<String> key = fileMap.keySet();

        // 遍历，获取文件SHA摘要
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String tempKey = (String) it.next();
            shaMap.put(tempKey, FileUtils.encryptFileToSHA(fileMap.get(tempKey)));
        }
        return shaMap;
    }

    /**
     * 生成pass.json文件
     * @param tempPath 
     * @param alipassModel
     * @param alipassresult
     * @return
     */
    public static Result passJsonFileCreate(String tempPath, AlipassModel alipassModel,
                                            Result alipassresult) {

        String passData = null;
        try {
            passData = JsonUtils.toJson(alipassModel);

            // 替换以下内容：
            // textMessage 为 message
            // eventTicketOperation、boardingPassOperation 为operation
            passData = JsonUtils.replace(passData);
        } catch (IOException e) {
            e.printStackTrace();
            alipassresult = Result.JSON_TRANSFORM_ERROR;
        }

        if (alipassresult.equals(Result.SUCCESS) && StringUtil.isNotBlank(passData)) {
            //生成pass.json中间文件，在完成alipass文件的生成后，需要删掉
            FileUtils.saveFile(tempPath + FileName.pass.getFileName(), passData);
        }

        return alipassresult;
    }

    /**
     * 生成alipass的zip压缩文件二进制流
     * @param tempPath 
     * 
     * @param fileList
     * @param dir
     * @param alipassModel
     * @return
     */
    private static String createPassFile(String tempPath, List<File> fileList,
                                         AlipassModel alipassModel) {
        String zipFile = tempPath + alipassModel.getPlatform().getChannelID()
                         + alipassModel.getFileInfo().getSerialNumber() + ".alipass";
        for (File file : fileList) {
            if (!file.exists()) {
                throw new RuntimeException("压缩的必要文件不存在！");
            }
        }
        BufferedInputStream bis = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            for (File file : fileList) {
                if (bis != null) {
                    bis.close();
                    bis = null;
                }
                bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(file.getName());
                out.putNextEntry(entry);
                int count;
                byte data[] = new byte[FileUtils.BUFFER];
                while ((count = bis.read(data, 0, FileUtils.BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
            }
            bis.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return zipFile;
    }

}
