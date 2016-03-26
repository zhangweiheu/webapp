package com.shop.controller.api;

import com.shop.annotation.LoginRequired;
import com.shop.bean.JsonResponse;
import com.shop.bean.Page;
import com.shop.bean.vo.GoodsVo;
import com.shop.model.Goods;
import com.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang on 2016/3/8.
 */
@RestController
@LoginRequired
@RequestMapping("/api/goods")
public class GoodsApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsApiController.class);

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResponse getGoodsList(@ModelAttribute Page page) {
        List<Goods> goodsList = goodsService.listAllGoods(page.getOffset(), page.getPageSize());
        List<GoodsVo> goodsVos = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVo goodsVo = new GoodsVo();
            BeanUtils.copyProperties(goods, goodsVo);
            goodsVo.setProperties("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getCreateAt()));
            goodsVo.setCreateAt(null);
            goodsVo.setUpdateAt(null);
            goodsVos.add(goodsVo);
        }
        page.setTotalCount(goodsService.getTotalCount());
        page.setData(goodsVos);
        return JsonResponse.success(page);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public JsonResponse updateGoods(GoodsVo goodsVo) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVo, goods);
        goodsService.updateGoods(goods);
        return JsonResponse.success();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JsonResponse saveGoods(GoodsVo goodsVo) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVo, goods);
        goods.setCreateAt(new Date());
        goods.setCreateAt(new Date());
        goodsService.saveGoods(goods);
        return JsonResponse.success();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public JsonResponse deleteGoods(@RequestParam("gid") int gid) {
        goodsService.deleteGoodsById(gid);
        return JsonResponse.success();
    }

}
