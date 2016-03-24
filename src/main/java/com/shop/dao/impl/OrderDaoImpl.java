package com.shop.dao.impl;

import com.shop.dao.OrderDao;
import com.shop.mapper.OrderMapper;
import com.shop.model.Order;
import com.shop.model.OrderCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> listAllOrder(int offset, int pageSize) {
        OrderCondition condition = new OrderCondition();
        condition.setLimitOffset(offset);
        condition.setLimitSize(pageSize);
        return orderMapper.selectByCondition(condition);
    }

    @Override
    public int countAllOrderByAttr(Order order) {
        return orderMapper.countByCondition(convertOrder2Condition(null, null, order));
    }

    @Override
    public List<Order> listOrdersByAttr(int offset, int pageSize, Order order) {
        return orderMapper.selectByCondition(convertOrder2Condition(offset, pageSize, order));
    }

    @Override
    public Order findOrderById(int Oid) {
        return orderMapper.selectById(Oid);
    }

    @Override
    public Order findOrderByUid(int Uid) {
        Order order = new Order();
        order.setUserId(Uid);
        List<Order> list = orderMapper.selectByCondition(convertOrder2Condition(null, null, order));
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public int deleteOrdersById(int Oid) {
        return orderMapper.deleteById(Oid);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public int saveOrder(Order order) {
        return orderMapper.insert(order);
    }

    private OrderCondition convertOrder2Condition(Integer offset, Integer pageSize, Order order) {
        OrderCondition condition = new OrderCondition();
        if (null != offset) {
            condition.setLimitOffset(offset);
        }

        if (null != pageSize) {
            condition.setLimitSize(pageSize);
        }

        if (null == order) {
            return condition;
        }

        if (null != order.getId()) {
            condition.createCriteria().andIdEqualTo(order.getId());
        }

        if (null != order.getExpressStatus()) {
            condition.createCriteria().andExpressStatusEqualTo(order.getExpressStatus());
        }

        if (null != order.getUserId()) {
            condition.createCriteria().andUserIdEqualTo(order.getUserId());
        }

        if (null != order.getOrderPrice()) {
            condition.createCriteria().andOrderPriceEqualTo(order.getOrderPrice());
        }
        return condition;
    }
}
