package com.shop.dao.impl;

import com.shop.dao.PassDao;
import com.shop.mapper.PassMapper;
import com.shop.model.Pass;
import com.shop.model.PassCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Repository
public class PassDaoImpl implements PassDao {

    @Autowired
    PassMapper passMapper;

    @Override
    public int countPassByAttr(Pass pass) {
        return passMapper.countByCondition(convertPassAttr2Condition(null, null, pass));
    }

    @Override
    public Pass findPassById(int pid) {
        return passMapper.selectById(pid);
    }

    @Override
    public int savePass(Pass pass) {
        return passMapper.insert(pass);
    }

    @Override
    public int updatePass(Pass pass) {
        return passMapper.updateByIdSelective(pass);
    }

    @Override
    public List<Pass> listAllPassByAttr(Integer offset, Integer pageSize, Pass pass) {
        return passMapper.selectByCondition(convertPassAttr2Condition(offset, pageSize, pass));
    }

    private PassCondition convertPassAttr2Condition(Integer offset, Integer pageSize, Pass pass) {
        PassCondition condition = new PassCondition();

        if (null != offset) {
            condition.setLimitOffset(offset);
        }

        if (null != pageSize) {
            condition.setLimitSize(pageSize);
        }

        if (null == pass) {
            return condition;
        }

        if (null != pass.getId()) {
            condition.createCriteria().andIdEqualTo(pass.getId());
        }

        if (null != pass.getStatus()) {
            condition.createCriteria().andStatusEqualTo(pass.getStatus());
        }

        if (null != pass.getSerialNumber()) {
            condition.createCriteria().andSerialNumberEqualTo(pass.getSerialNumber());
        }
        return condition;
    }

}
