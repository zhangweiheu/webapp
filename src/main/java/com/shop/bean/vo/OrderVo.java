package com.shop.bean.vo;

import com.shop.bean.BaseObject;
import com.shop.core.model.OrderDetail;
import com.shop.core.mybatis.enums.ExpressStatusEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by zhang on 2016/3/27.
 */
public class OrderVo extends BaseObject {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单金额
     */
    private Double orderPrice;

    /**
     * 0:处理中 | 1:快递已发货 | 2:运送中 | 3:待签收
     */
    private ExpressStatusEnum expressStatus;


    private List<OrderDetail> orderDetailList;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;


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

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
