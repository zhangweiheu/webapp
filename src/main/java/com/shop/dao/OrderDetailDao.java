package com.shop.dao;

import com.shop.model.OrderDetail;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
public interface OrderDetailDao {
    List<OrderDetail> listOrderDetailByAttr(OrderDetail orderDetail);

    int countAllOrderByAttr(OrderDetail orderDetail);

    List<OrderDetail> listCart(int offset, int pageSize, OrderDetail orderDetail);

    OrderDetail findOrderDetailById(int ODid);

    int deleteOrderDetailById(int ODid);

    int updateOrderDetail(OrderDetail orderDetail);

    int saveOrderDetail(OrderDetail orderDetail);
}
