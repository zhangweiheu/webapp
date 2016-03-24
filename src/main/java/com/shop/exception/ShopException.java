package com.shop.exception;

/**
 * Created by quxiao on 2015/4/17.
 */
public class ShopException extends RuntimeException {
    public ShopException() {
        this("出现异常");
    }

    public ShopException(String message) {
        super(message);
    }
}
