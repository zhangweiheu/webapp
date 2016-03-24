package com.shop.dao;

import com.shop.model.Pass;

import java.util.List;

/**
 * Created by zhang on 2016/3/24.
 */
public interface PassDao {
    Pass findPassById(int pid);

    int savePass(Pass pass);

    int updatePass(Pass pass);

    List<Pass> listAllPassByAttr(Pass pass);
}
