package com.shop.model;

import com.shop.mybatis.enums.ExpressStatusEnum;
import java.io.Serializable;
import java.util.Date;

/**
 * shop.order  
 *
 * @author zhang
 * @date 2016-3-26
 *
 */
public class Order implements Serializable {
    /** 订单id */
    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 订单金额 */
    private Double orderPrice;

    /** 0:处理中 | 1:快递已发货 | 2:运送中 | 3:待签收 */
    private ExpressStatusEnum expressStatus;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public ExpressStatusEnum getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(ExpressStatusEnum expressStatus) {
        this.expressStatus = expressStatus;
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