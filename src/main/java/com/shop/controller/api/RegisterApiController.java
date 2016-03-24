package com.shop.controller.api;

import com.shop.annotation.NotNeedLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhang on 2016/2/20.
 */
@RestController
@NotNeedLogin
@Deprecated
@RequestMapping("/api/register")
public class RegisterApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterApiController.class);
}
