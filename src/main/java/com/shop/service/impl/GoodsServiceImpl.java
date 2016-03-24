package com.shop.service.impl;

import com.shop.model.Goods;
import com.shop.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public boolean deleteGoodsById(int Gid) {
        return false;
    }

    @Override
    public int saveGoods(Goods goods) {
        return 0;
    }

    @Override
    public int updateGoods(Goods goods) {
        return 0;
    }

    @Override
    public List<Goods> listAllGoods(int offset, int pageSize) {
        return null;
    }
}
