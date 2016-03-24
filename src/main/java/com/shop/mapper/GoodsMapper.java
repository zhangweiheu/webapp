package com.shop.mapper;

import com.shop.model.Goods;
import com.shop.model.GoodsCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    int countByCondition(GoodsCondition example);

    int deleteByCondition(GoodsCondition example);

    int deleteById(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByCondition(GoodsCondition example);

    Goods selectById(Integer id);

    int updateByConditionSelective(@Param("record") Goods record, @Param("example") GoodsCondition example);

    int updateByCondition(@Param("record") Goods record, @Param("example") GoodsCondition example);

    int updateByIdSelective(Goods record);

    int updateById(Goods record);
}