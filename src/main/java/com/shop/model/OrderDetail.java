package com.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * shop.order_detail  
 *
 * @author zhang
 * @date 2016-3-24
 *
 */
public class OrderDetail implements Serializable {
    /**  */
    private Integer id;

    /** 关联订单id */
    private Integer orderId;

    /** 商品id */
    private Integer goodsId;

    /** 商品数量 */
    private String goodsCount;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 更新时间 */
    private Date updateAt;

    /** 创建时间 */
    private Date createAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount == null ? null : goodsCount.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}