package com.data.config;

import com.data.enums.mybatis.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by 36kr on 16/1/28.
 */
@Configuration
@Import(CoreDataSourceConfiguration.class)
@MapperScan(basePackageClasses = {com.data.mapper.Pkg.class}, sqlSessionFactoryRef = MyBatisConfiguration.SQL_SESSION_FACTORY_NAME)
public class MyBatisConfiguration {
    public static final String SQL_SESSION_FACTORY_NAME = "exams_online_SqlSessionFactory";

    private String enumBasePackages = "enums";

    @Bean(name = MyBatisConfiguration.SQL_SESSION_FACTORY_NAME)
    public MybatisSqlSessionFactoryBean exams_online_MybatisSqlSessionFactoryBean(CoreDataSourceConfiguration config) {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(config.core_dataSource());
        sessionFactoryBean.setEnumBasePackages(enumBasePackages);
        return sessionFactoryBean;
    }
}
