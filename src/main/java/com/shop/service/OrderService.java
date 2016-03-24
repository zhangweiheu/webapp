package com.shop.service;


import com.shop.model.Order;

import java.util.List;

/**
 * Created by zhangwei on 16/1/25.
 */

public interface OrderService {
    int getTotalCount();

    boolean deleteOrderById(int Oid);

    int saveOrder(Order order);

    int updateOrder(Order order);

    List<Order> listAllOrder(int offset, int pageSize);
}
