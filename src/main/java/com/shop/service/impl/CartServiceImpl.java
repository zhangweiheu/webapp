package com.shop.service.impl;

import com.shop.model.OrderDetail;
import com.shop.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class CartServiceImpl implements CartService{
    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public boolean deleteCartByODid(int ODid) {
        return false;
    }

    @Override
    public int saveCart(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int updateCart(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public List<OrderDetail> listAllCart(int offset, int pageSize) {
        return null;
    }
}
