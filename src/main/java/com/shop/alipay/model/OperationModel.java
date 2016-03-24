/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 操作属性Model
 * @author siyu.jsy
 * @version $Id: OperationModel.java,v 0.1 2013-5-2 下午5:21:27 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class OperationModel extends BaseModel {

    private static final long      serialVersionUID = -7231783601716462985L;

    /**操作区展示类型*/
    @JsonProperty("format")
    private String                 opFormat;

    /**操作区展示信息*/
    @JsonProperty("message")
    private String                 opMessage;

    /**text format mesage*/
    private List<TextMessageModel> textMessage;

    /**app format message*/
    private AppDetailModel         appMessage;

    /**img format message*/
    private ImgModel               imgMessage;

    /**展示信息编码格式*/
    @JsonProperty("messageEncoding")
    private String                 opMessageEncoding;

    /**显示在操作区的功能名称*/
    @JsonProperty("altText")
    private String                 opLabel;

    public String getOpFormat() {
        return opFormat;
    }

    public void setOpFormat(String opFormat) {
        this.opFormat = opFormat;
    }

    public String getOpMessage() {
        return opMessage;
    }

    public void setOpMessage(String opMessage) {
        this.opMessage = opMessage;
    }

    public AppDetailModel getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(AppDetailModel appMessage) {
        this.appMessage = appMessage;
    }

    public String getOpMessageEncoding() {
        return opMessageEncoding;
    }

    public void setOpMessageEncoding(String opMessageEncoding) {
        this.opMessageEncoding = opMessageEncoding;
    }

    public List<TextMessageModel> getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(List<TextMessageModel> textMessage) {
        this.textMessage = textMessage;
    }

    public String getOpLabel() {
        return opLabel;
    }

    public void setOpLabel(String opLabel) {
        this.opLabel = opLabel;
    }

    /**
     * Getter method for property <tt>imgMessage</tt>.
     * 
     * @return property value of imgMessage
     */
    public ImgModel getImgMessage() {
        return imgMessage;
    }

    /**
     * Setter method for property <tt>imgMessage</tt>.
     * 
     * @param imgMessage value to be assigned to property imgMessage
     */
    public void setImgMessage(ImgModel imgMessage) {
        this.imgMessage = imgMessage;
    }

}
