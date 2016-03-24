/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;

import java.util.Collection;
import java.util.Map;

/**
 * List 工具类
 * @author siyu.jsy
 * @version $Id: ListUtils.java,v 0.1 2013-5-3 下午10:14:20 siyu.jsy Exp $
 */
public final class CollectionUtils {

    /**
     * 判断List是否为空 或者 元素为0
     * @param sourceList
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.size() == 0);
    }

    /**
     * 判断Map不为空
     * 
     * @param map map对象
     * @return ture为不空,false为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

}
