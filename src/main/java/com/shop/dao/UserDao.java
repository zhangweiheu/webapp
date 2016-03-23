package com.shop.dao;


import com.shop.model.User;

import java.util.List;

/**
 * Created by 36kr on 16/1/25.
 */
public interface UserDao {

    User findById(int id);

    int deleteById(int id);

    List<User> listAllUser(int offset, int size);

    User findUserByName(String name);

    int saveUser(User user);

    int updateUser(User user);

    int CountByProperty(User user);
}
