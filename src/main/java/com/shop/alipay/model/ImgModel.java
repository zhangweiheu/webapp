package com.shop.alipay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ImgModel extends BaseModel {

    private static final long serialVersionUID = -1396389051964038776L;

    /**img file url*/
    private String img;

    /**target url*/
    private String target;

    /**
     * Getter method for property <tt>img</tt>.
     * 
     * @return property value of img
     */
    public String getImg() {
        return img;
    }

    /**
     * Setter method for property <tt>img</tt>.
     * 
     * @param img value to be assigned to property img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Getter method for property <tt>target</tt>.
     * 
     * @return property value of target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Setter method for property <tt>target</tt>.
     * 
     * @param target value to be assigned to property target
     */
    public void setTarget(String target) {
        this.target = target;
    }
    
}
