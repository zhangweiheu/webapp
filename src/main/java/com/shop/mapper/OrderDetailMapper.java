package com.shop.mapper;

import com.shop.model.OrderDetail;
import com.shop.model.OrderDetailCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    int countByCondition(OrderDetailCondition example);

    int deleteByCondition(OrderDetailCondition example);

    int deleteById(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByCondition(OrderDetailCondition example);

    OrderDetail selectById(Integer id);

    int updateByConditionSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailCondition example);

    int updateByCondition(@Param("record") OrderDetail record, @Param("example") OrderDetailCondition example);

    int updateByIdSelective(OrderDetail record);

    int updateById(OrderDetail record);
}