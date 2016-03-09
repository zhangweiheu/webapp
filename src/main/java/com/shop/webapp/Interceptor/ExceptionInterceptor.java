package com.shop.webapp.Interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by zhangwei on 16/1/25.
 */
@ControllerAdvice()
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
}
