/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

/**
 * Pass特性 EInfo的最小单元，用于组合成复杂的EInfo对象
 * @author siyu.jsy
 * @version $Id: EInfoUnitModel.java,v 0.1 2013-5-2 下午3:45:11 siyu.jsy Exp $
 */
@JsonInclude(Include.NON_NULL)
public class EInfoUnitModel extends BaseModel {

    private static final long   serialVersionUID = 1138319623812848266L;

    /**字段关键字*/
    private String              key;

    /**显示名称*/
    private String              label;

    /**显示具体值*/
    private String              value;

    /**字段类型:具体取值见{@see com.alipay.alipass.sdk.enums.OperationFormatType}的typeName属性*/
    private String              type;

    /**会员卡类型中加入*/
    private String              icon;

    /**会员卡类型中加入*/
    private Map<String, Object> more;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>icon</tt>.
     * 
     * @return property value of icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Setter method for property <tt>icon</tt>.
     * 
     * @param icon value to be assigned to property icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Getter method for property <tt>more</tt>.
     * 
     * @return property value of more
     */
    public Map<String, Object> getMore() {
        return more;
    }

    /**
     * Setter method for property <tt>more</tt>.
     * 
     * @param more value to be assigned to property more
     */
    public void setMore(Map<String, Object> more) {
        this.more = more;
    }

}
