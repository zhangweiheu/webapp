package com.shop.controller;

import com.shop.annotation.LoginRequired;
import com.shop.bean.UserHolder;
import com.shop.bean.vo.UserVo;
import com.shop.model.User;
import com.shop.service.UserService;
import com.shop.util.PhotoUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangwei on 16/1/25.
 */
@Controller
@LoginRequired
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String User(ModelMap view) {
        User user = UserHolder.getInstance().getUser();
        view.addAttribute(user);
        return "user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditUser(ModelMap view) {
        User user = UserHolder.getInstance().getUser();
        view.addAttribute(user);
        return "edit_user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(UserVo userVo, HttpServletRequest request) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setAvatar(PhotoUploadUtil.uploadPhoto(userVo.getFile(), request, user.getId()));
        userService.updateUser(user);
        return "redirect:";
    }
}
