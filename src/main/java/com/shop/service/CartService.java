package com.shop.service;


import com.shop.model.OrderDetail;

import java.util.List;

/**
 * Created by zhangwei on 16/1/25.
 */

public interface CartService {
    int getTotalCount();

    boolean deleteCartByODid(int ODid);

    int saveCart(OrderDetail orderDetail);

    int updateCart(OrderDetail orderDetail);

    List<OrderDetail> listAllCart(int offset, int pageSize);
}
