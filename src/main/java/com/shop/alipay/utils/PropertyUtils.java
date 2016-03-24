/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties文件工具类
 * @author siyu.jsy
 * @version $Id: PropertyUtils.java,v 0.1 2013-5-4 下午3:28:03 siyu.jsy Exp $
 */
public final class PropertyUtils {
    // 创建对象
    static PropertyUtils prop;

    private PropertyUtils() {
        super();
    }

    /**
     * 单例
     * @return
     */
    public static PropertyUtils getInstance() {
        if (prop == null)
            // 创建EnvironmentConfig对象
            prop = new PropertyUtils();
        // 返回EnvironmentConfig对象
        return prop;
    }

    /**
     * 根据传入的配置文件路径 及 key，获取响应的Value,同时可以指定默认值，用于未获取到正确值时返回。
     * 
     * @param fileName
     * @param strKey
     * @param aultValue
     * @return
     * @throws IOException 
     */
    public String getPropertyValue(String fileName, String strKey, String defaultValue)
                                                                                       throws IOException {
        String result = null;

        if (StringUtil.isNotBlank(fileName) && StringUtil.isNotBlank(strKey)) {
            Properties p = getProperties(fileName);

            result = p.getProperty(strKey, defaultValue);
        }
        return result;
    }

    /**
     *  根据文件名及路径，读取文件内容
     * @param fileName
     * @return
     * @throws IOException 
     */
    private Properties getProperties(String fileName) throws IOException {

        Properties properties = null;
        if (StringUtil.isNotBlank(fileName)) {

            properties = new Properties();

            InputStream is = this.getClass().getResourceAsStream(fileName);
            properties.load(is);
        }
        return properties;
    }

}
