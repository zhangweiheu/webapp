/**
 *
 */
package com.shop.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * application context配置
 *
 * @author tanxianwen 2015年9月25日
 */
@Configuration
@EnableTransactionManagement
public class CoreDataSourceConfiguration implements TransactionManagementConfigurer {
    public static final String ONLINE_EXAMS_TM = "com_shop_tm";

    @Bean(destroyMethod = "close")
    public DataSource core_dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setUsername("yangyu");
            druidDataSource.setPassword("yangyu");
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            druidDataSource.setUrl("jdbc:mysql://192.168.1.115:3306/shop?autoCommit=true&autoReconnect=true&useUnicode=true&tinyInt1isBit=false&zeroDateTimeBehavior=round&characterEncoding=UTF-8&yearIsDateType=false");
            druidDataSource.setConnectionProperties("druid.stat.mergeSql=true");
            druidDataSource.setFilters("stat,wall"); // 配置监控统计拦截的filters
            druidDataSource.setInitialSize(10);// 配置初始化大小、最小、最大
            druidDataSource.setMinIdle(10);
            druidDataSource.setMaxActive(100);
            druidDataSource.setMaxWait(60000); // 配置获取连接等待超时的时间
            druidDataSource.setTimeBetweenEvictionRunsMillis(60000);// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            druidDataSource.setMinEvictableIdleTimeMillis(300000); // 配置一个连接在池中最小生存的时间，单位是毫秒
            druidDataSource.setValidationQuery("SELECT 'x'");
            druidDataSource.setTestWhileIdle(true);
            druidDataSource.setTestOnBorrow(false);
            druidDataSource.setTestOnReturn(false);
            druidDataSource.setPoolPreparedStatements(true); // 打开PSCache，并且指定每个连接上PSCache的大小
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return druidDataSource;
    }

    @Bean(name = CoreDataSourceConfiguration.ONLINE_EXAMS_TM)
    public DataSourceTransactionManager examsDataSourceTm() {
        return new DataSourceTransactionManager(core_dataSource());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return examsDataSourceTm();
    }

}
