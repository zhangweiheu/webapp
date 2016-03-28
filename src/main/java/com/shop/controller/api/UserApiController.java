package com.shop.controller.api;


import com.shop.annotation.LoginRequired;
import com.shop.bean.JsonResponse;
import com.shop.bean.UserHolder;
import com.shop.bean.vo.UserVo;
import com.shop.core.model.User;
import com.shop.core.service.UserService;
import com.shop.core.util.PhotoUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zhangwei on 16/1/25.
 */
@RestController
@LoginRequired
@RequestMapping("/api/user")
public class UserApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public JsonResponse getUserById(@PathVariable("uid") int uid) {
        if (!checkprivilege(uid)) {
            return JsonResponse.failed();
        }
        User user = userService.findUserByUid(uid);
        user.setPassword(null);
        return JsonResponse.success(user);
    }



    @RequestMapping(value = "", method = RequestMethod.PUT)
    public JsonResponse updateUser(User user) {
        if(!checkprivilege(user.getId())){
            return JsonResponse.failed();
        }
        return JsonResponse.success(userService.updateUser(user));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JsonResponse saveUser(UserVo userVo, HttpServletRequest request) {
        if (null != userService.findUserByName(userVo.getUsername())) {
            return JsonResponse.failed("已存在相同用户名");
        }
        if(null == userVo.getId()){
            User user = new User();
            BeanUtils.copyProperties(userVo, user);
            user.setCreateAt(new Date());
            user.setUpdateAt(new Date());
            userService.saveUser(user);
            if (null != userVo.getFile()) {
                user.setAvatar(PhotoUploadUtil.uploadPhoto(userVo.getFile(), request, user.getId()));
            }
            userService.updateUser(user);
        }else {
            User user = new User();
            BeanUtils.copyProperties(userVo, user);
            user.setUpdateAt(new Date());
            if (null != userVo.getFile()) {
                user.setAvatar(PhotoUploadUtil.uploadPhoto(userVo.getFile(), request, user.getId()));
            }
            userService.updateUser(user);
        }

        return JsonResponse.success();
    }

    private Boolean checkprivilege(Integer uid) {
        return uid.equals(UserHolder.getInstance().getUser().getId()) || UserHolder.getInstance().getUser().getIsAdmin();
    }
}
