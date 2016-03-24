/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;


import com.shop.alipay.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * alipass模板解析工具类
 * <p>
 *    <ul>包括以下功能：
 *        <li>1、模板动态参数解析。将模板文件中所有$$标识的参数解析出来，保存到<code>Set</code>集合中</br>
 *        例如"name" : $name$, "age" : $age$，返回结果为[name, age]
 *        <li>2、模板同步。根据传入的<code>Map</code>集合，替换动态参数key对应的值</br>
 *        例如map为<"name", "王二">，则替换模板中$name$变量，返回结果为"name" : "王二", "age" : null
 *    </ul>
 * </p>
 * @author hengchang.yu
 * @version $Id: ParseService.java, v 0.1 2013年11月6日 下午1:44:46 hengchang.yu Exp $
 */
public class ParseUtils {
    /**
     * 模板动态参数解析。</br>
     * 将模板文件中所有$$标识的参数解析出来，保存到<code>Set</code>集合中。<br>
     * 
     * @param templateStr String类型的模板对象
     * @return 模板动态参数集合
     * @throws Throwable 
     */
    public static Set<String> parseTemplateParamKeys(String templateStr) throws Throwable {
        //解析模板对象，获取动态参数集合
        InputStream input = null;
        Set<String> paramKeys = null;
        try {
            input = new ByteArrayInputStream(templateStr.getBytes());
            Parser parser = new Parser(input);
            paramKeys = parser.getParamKeys();
        } catch (Exception e) {
            throw e;
        } catch (Throwable t) {
            throw t;
        } finally {
            if (null != input) {
                input.close();
            }
        }
        return paramKeys;
    }

    /**
     * 模板动态参数同步。</br>
     * 根据传入的<code>Map</code>集合，替换动态参数key对应的值。</br>
     * 
     * @param templateStr String类型的模板对象
     * @param map         模板动态参数集合
     * @return 模板对象
     * @throws Throwable 
     */
    public static StringBuffer syncTemplate(String templateStr, Map<String, String> map)
                                                                                        throws Throwable {
        StringBuffer buffer = null;
        if (map == null || map.size() == 0) {
            return StringUtil.isBlank(templateStr) ? buffer : new StringBuffer(templateStr);
        }
        InputStream input = null;
        try {

            input = new ByteArrayInputStream(templateStr.getBytes());
            Parser parser = new Parser(input);

            //解析并替换动态参数的key对应的value
            buffer = parser.setConfig(map).start();

        } catch (Exception e) {
            throw e;
        } catch (Throwable t) {
            throw t;
        } finally {
            if (null != input) {
                input.close();
            }
        }
        return buffer;
    }

}
