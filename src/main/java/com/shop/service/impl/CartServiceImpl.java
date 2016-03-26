package com.shop.service.impl;

import com.shop.dao.OrderDetailDao;
import com.shop.model.OrderDetail;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    public int getTotalCountByUid(int uid) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUserId(uid);
        return orderDetailDao.countAllOrderByAttr(orderDetail);
    }

    @Override
    public int deleteCartByODid(int odid) {
        return orderDetailDao.deleteOrderDetailById(odid);
    }

    @Override
    public int saveCart(OrderDetail orderDetail) {
        return orderDetailDao.saveOrderDetail(orderDetail);
    }

    @Override
    public int updateCart(OrderDetail orderDetail) {
        return orderDetailDao.updateOrderDetail(orderDetail);
    }

    @Override
    public List<OrderDetail> listUserAllCart(int offset, int pageSize, int uid) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUserId(uid);
        return orderDetailDao.listCart(offset, pageSize, orderDetail);
    }
}
