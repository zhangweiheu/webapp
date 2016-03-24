package com.shop.service.impl;

import com.shop.model.Order;
import com.shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public boolean deleteOrderById(int Oid) {
        return false;
    }

    @Override
    public int saveOrder(Order order) {
        return 0;
    }

    @Override
    public int updateOrder(Order order) {
        return 0;
    }

    @Override
    public List<Order> listAllOrder(int offset, int pageSize) {
        return null;
    }
}
