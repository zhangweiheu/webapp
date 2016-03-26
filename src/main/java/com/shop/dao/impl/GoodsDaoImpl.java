package com.shop.dao.impl;

import com.shop.dao.GoodsDao;
import com.shop.mybatis.enums.GoodsStatusEnum;
import com.shop.mapper.GoodsMapper;
import com.shop.model.Goods;
import com.shop.model.GoodsCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> listAllGoods(int offset, int pageSize) {
        Goods goods = new Goods();
        goods.setStatus(GoodsStatusEnum.NORMAL);
        return goodsMapper.selectByCondition(convertGoodsAttr2Condition(offset, pageSize, goods));
    }

    @Override
    public int countAllGoodsByAttr(Goods goods) {
        return goodsMapper.countByCondition(convertGoodsAttr2Condition(null, null, goods));
    }

    @Override
    public List<Goods> listGoodsByAttr(int offset, int pageSize, Goods goods) {
        return goodsMapper.selectByCondition(convertGoodsAttr2Condition(offset, pageSize, goods));
    }

    @Override
    public Goods findGoodsById(int Gid) {
        return goodsMapper.selectById(Gid);
    }

    @Override
    public int deleteGoodsById(int Gid) {
        Goods goods = new Goods();
        goods.setId(Gid);
        goods.setStatus(GoodsStatusEnum.REMOVE_SUPPORT);
        return goodsMapper.updateById(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateById(goods);
    }

    @Override
    public int saveGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    private GoodsCondition convertGoodsAttr2Condition(Integer offset, Integer pageSize, Goods goods) {
        GoodsCondition condition = new GoodsCondition();

        if (null != offset) {
            condition.setLimitOffset(offset);
        }

        if (null != pageSize) {
            condition.setLimitSize(pageSize);
        }

        if (null == goods) {
            return condition;
        }

        if (null != goods.getId()) {
            condition.createCriteria().andIdEqualTo(goods.getId());
        }

        if (null != goods.getName()) {
            condition.createCriteria().andNameEqualTo(goods.getName());
        }

        if (null != goods.getPrice()) {
            condition.createCriteria().andPriceEqualTo(goods.getPrice());
        }

        if (null != goods.getProviderId()) {
            condition.createCriteria().andProviderIdEqualTo(goods.getProviderId());
        }

        if (null != goods.getProviderPhone()) {
            condition.createCriteria().andProviderPhoneEqualTo(goods.getProviderPhone());
        }

        if (null != goods.getProviderName()) {
            condition.createCriteria().andProviderNameEqualTo(goods.getProviderName());
        }

        if (null != goods.getRemain()) {
            condition.createCriteria().andRemainEqualTo(goods.getRemain());
        }

        if (null != goods.getStatus()) {
            condition.createCriteria().andStatusEqualTo(goods.getStatus());
        }

        if (null != goods.getSellCount()) {
            condition.createCriteria().andSellCountEqualTo(goods.getSellCount());
        }
        return condition;
    }
}
