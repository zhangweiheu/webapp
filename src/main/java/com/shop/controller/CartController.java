package com.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangwei on 16/1/25.
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(ModelMap view) {

        return "cart";
    }
}
