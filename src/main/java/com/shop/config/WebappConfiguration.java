package com.shop.config;

import com.shop.service.Pkg;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by zhangwei on 16/1/15.
 */
@Configuration
@EnableAutoConfiguration
@Import({MyBatisConfiguration.class})
@ComponentScan(basePackageClasses = {com.shop.service.Pkg.class})
public class WebappConfiguration {
}

