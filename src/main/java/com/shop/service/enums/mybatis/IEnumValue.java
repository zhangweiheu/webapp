/**
 * 
 */
package com.shop.service.enums.mybatis;

/**
 * @author xianwen.tan
 *
 */
public interface IEnumValue {

    /**
     * 获取枚举的int值，只有实现该接口的枚举才能被mybatis自动转换到数据库
     * 
     * @return
     */
    int getValue();

}
