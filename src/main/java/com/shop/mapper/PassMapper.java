package com.shop.mapper;

import com.shop.model.Pass;
import com.shop.model.PassCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PassMapper {
    int countByCondition(PassCondition example);

    int deleteByCondition(PassCondition example);

    int deleteById(Integer id);

    int insert(Pass record);

    int insertSelective(Pass record);

    List<Pass> selectByCondition(PassCondition example);

    Pass selectById(Integer id);

    int updateByConditionSelective(@Param("record") Pass record, @Param("example") PassCondition example);

    int updateByCondition(@Param("record") Pass record, @Param("example") PassCondition example);

    int updateByIdSelective(Pass record);

    int updateById(Pass record);
}