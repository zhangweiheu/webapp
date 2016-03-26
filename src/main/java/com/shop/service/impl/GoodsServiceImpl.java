package com.shop.service.impl;

import com.shop.dao.GoodsDao;
import com.shop.mybatis.enums.GoodsStatusEnum;
import com.shop.model.Goods;
import com.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public Goods findGoodsById(int gid) {
        return goodsDao.findGoodsById(gid);
    }

    @Override
    public int getTotalCount() {
        Goods goods = new Goods();
        goods.setStatus(GoodsStatusEnum.NORMAL);
        return goodsDao.countAllGoodsByAttr(goods);
    }

    @Override
    public boolean deleteGoodsById(int Gid) {
        return goodsDao.deleteGoodsById(Gid) > 0;
    }

    @Override
    public int saveGoods(Goods goods) {
        return goodsDao.saveGoods(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public List<Goods> listAllGoods(int offset, int pageSize) {
        return goodsDao.listAllGoods(offset, pageSize);
    }
}
