package com.shop.controller.api;

import com.alibaba.druid.support.json.JSONUtils;
import com.shop.annotation.NotNeedLogin;
import com.shop.bean.JsonResponse;
import com.shop.geetest.GeetestConfig;
import com.shop.geetest.GeetestLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhang on 2016/2/24.
 */
@RestController
@NotNeedLogin
@RequestMapping("/api/token")
public class TokenApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenApiController.class);

    @RequestMapping(value = "/geetest", method = RequestMethod.GET)
    public JsonResponse login(HttpServletRequest request) {
        GeetestLib geetestLib = new GeetestLib(GeetestConfig.getCaptchaId(), GeetestConfig.getPrivateKey());
        int gtServerStatus = geetestLib.preProcess();
        request.getSession().setAttribute(geetestLib.gtServerStatusSessionKey, gtServerStatus);
        return JsonResponse.success().add(JSONUtils.parse(geetestLib.getResponseStr()));
    }
}
