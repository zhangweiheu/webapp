package com.shop.webapp.controller.api;

import com.shop.service.bean.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhang on 2016/2/20.
 */
@RestController
@RequestMapping("/api/login")
public class LoginApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public JsonResponse login(HttpServletRequest request) {
        return JsonResponse.success();
    }
}
