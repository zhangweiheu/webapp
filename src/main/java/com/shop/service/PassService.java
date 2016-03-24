package com.shop.service;


import com.shop.model.Pass;

import java.util.List;

/**
 * Created by zhangwei on 16/1/25.
 */

public interface PassService {
    int getTotalCount();

    boolean deletePassById(int Oid);

    int savePass(Pass pass);

    int updatePass(Pass pass);

    List<Pass> listAllPass(int offset, int pageSize);
}
