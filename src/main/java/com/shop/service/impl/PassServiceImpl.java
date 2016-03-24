package com.shop.service.impl;

import com.shop.model.Pass;
import com.shop.service.PassService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class PassServiceImpl implements PassService{
    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public boolean deletePassById(int Oid) {
        return false;
    }

    @Override
    public int savePass(Pass pass) {
        return 0;
    }

    @Override
    public int updatePass(Pass pass) {
        return 0;
    }

    @Override
    public List<Pass> listAllPass(int offset, int pageSize) {
        return null;
    }
}
