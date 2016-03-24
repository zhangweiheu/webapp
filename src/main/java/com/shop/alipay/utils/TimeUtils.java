/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;


import com.shop.alipay.enums.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间相关工具
 * @author siyu.jsy
 * @version $Id: TimeUtils.java,v 0.1 2013-5-4 下午10:58:05 siyu.jsy Exp $
 */
public final class TimeUtils {

    /**
     * 判断时间是否符合有效格式
     * @param date
     * @return
     */
    public static boolean isDateStringValid(String date) {

        boolean isValidDateStr = false;

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT.getValue());

        try {
            sdf.parse(date);
            isValidDateStr = true;
        } catch (ParseException parseExp) {

        }
        return isValidDateStr;
    }

    /**
     * 按照给定格式 格式化时间
     * @param date
     * @param format
     * @return
     */
    public static String formatTime(Date date, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
