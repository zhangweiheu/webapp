package com.shop.mapper;

import com.shop.model.Order;
import com.shop.model.OrderCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByCondition(OrderCondition example);

    int deleteByCondition(OrderCondition example);

    int deleteById(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByCondition(OrderCondition example);

    Order selectById(Integer id);

    int updateByConditionSelective(@Param("record") Order record, @Param("example") OrderCondition example);

    int updateByCondition(@Param("record") Order record, @Param("example") OrderCondition example);

    int updateByIdSelective(Order record);

    int updateById(Order record);
}