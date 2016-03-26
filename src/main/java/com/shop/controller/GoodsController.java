package com.shop.controller;

import com.shop.annotation.AdminOnly;
import com.shop.annotation.LoginRequired;
import com.shop.bean.vo.GoodsVo;
import com.shop.model.Goods;
import com.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhang on 2016/3/8.
 */
@Controller
@LoginRequired
@AdminOnly
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    GoodsService goodsService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String question() {
        return "system_goods";
    }

    @RequestMapping(value = "/edit/{gid}", method = RequestMethod.GET)
    public ModelAndView editGoods(@PathVariable("gid") int gid) {
        ModelAndView view = new ModelAndView();
        Goods goods = goodsService.findGoodsById(gid);
        if (null != goods) {
            GoodsVo goodsVo = new GoodsVo();
            BeanUtils.copyProperties(goods, goodsVo);
            goodsVo.setUpdateAt(null);
            goodsVo.setCreateAt(null);
            view.addObject(goodsVo);
        }
        view.setViewName("edit_goods");
        return view;
    }

}
