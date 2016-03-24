package com.shop.service;


import com.shop.model.Goods;

import java.util.List;

/**
 * Created by zhangwei on 16/1/25.
 */

public interface GoodsService {
    int getTotalCount();

    boolean deleteGoodsById(int Gid);

    int saveGoods(Goods goods);

    int updateGoods(Goods goods);

    List<Goods> listAllGoods(int offset, int pageSize);
}
