package com.shop.model;

import com.shop.mybatis.enums.PassStatusEnum;
import java.io.Serializable;
import java.util.Date;

/**
 * shop.pass  
 *
 * @author zhang
 * @date 2016-3-26
 *
 */
public class Pass implements Serializable {
    /** 优惠码id */
    private Integer id;

    /** 优惠码 */
    private Integer serialNumber;

    /** 优惠码状态 0未使用 | 1已使用 */
    private PassStatusEnum status;

    /** 创建时间 */
    private Date createAt;

    /** 更新时间 */
    private Date updateAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public PassStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PassStatusEnum status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}