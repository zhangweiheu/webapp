package com.shop.dao;

import com.shop.model.Goods;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
public interface GoodsDao {
    List<Goods> listAllGoods(int offset, int pageSize);

    int countAllGoodsByAttr(Goods goods);

    List<Goods> listGoodsByAttr(int offset, int pageSize,Goods goods);

    Goods findGoodsById(int Gid);

    int deleteGoodsById(int Gid);

    int updateGoods(Goods goods);

    int saveGoods(Goods goods);
}
