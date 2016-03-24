package com.shop.service.impl;

import com.shop.dao.UserDao;
import com.shop.model.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhangwei on 16/1/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listAllUser(int offset, int pageSize){
        return userDao.listAllUser(offset, pageSize);
    }

    @Override
    public int getTotalCount() {
        return userDao.CountByProperty(null);
    }

    @Override
    public User findUserByUid(Integer uid) {
        return userDao.findById(uid);
    }

    @Override
    public boolean deleteUserByUid(int uid) {
        return userDao.deleteById(uid) == 1;
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public int saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
