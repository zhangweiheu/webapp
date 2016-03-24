/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 样式属性Model
 * @author siyu.jsy
 * @version $Id: StyleModel.java,v 0.1 2013-5-2 下午7:10:56 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class StyleModel extends BaseModel {

    private static final long serialVersionUID = 3315881414934404638L;
    /**背景颜色*/
    private String            backgroundColor;

    /**背景图片**/
    private String            backgroupImg;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroupImg() {
        return backgroupImg;
    }

    public void setBackgroupImg(String backgroupImg) {
        this.backgroupImg = backgroupImg;
    }

}
