/**
 *
 */
package com.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * 通用配置
 *
 * @author tanxianwen 2015年10月10日
 */
@Configuration
public class CoreProperties {

    //mysql配置
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;
    @Value("${spring.datasource.connection-test-query}")
    private String dbTestQuery;

    // mongo db config
    @Value("${spring.data.mongodb.host}")
    private String mongoReplicaSet;
    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;
    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;
    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;
    @Value("${mongo.connectionsPerHost:100}")
    private int mongoConnectionsPerHost;
    @Value("${mongo.threadsAllowedToBlockForConnectionMultiplier:5}")
    private int mongoThreadsAllowedToBlockForConnectionMultiplier;
    @Value("${mongo.connectTimeout:1500}")
    private int mongoConnectTimeout;
    @Value("${mongo.maxWaitTime:1500}")
    private int mongoMaxWaitTime;
    @Value("${mongo.socketKeepAlive:true}")
    private boolean mongoSocketKeepAlive;
    @Value("${mongo.socketTimeout:1500}")
    private int mongoSocketTimeout;

    //rabbitmq配置
    @Value("${spring.rabbitmq.addresses}")
    private String rabbitMQAddresses;
    @Value("${spring.rabbitmq.username}")
    private String rabbitMQUsername;
    @Value("${spring.rabbitmq.password}")
    private String rabbitMQPassword;
    @Value("${spring.rabbitmq.queue}")
    private String rabbitMQQueue;
    @Value("${spring.rabbitmq.exchange}")
    private String rabbitMQExchange;


    //    @Value("${redis.host}")
    //    private String redisHostName;
    //    @Value("${redis.password}")
    //    private String redisPassword;
    //    @Value("${redis.port}")
    //    private int redisHostPort;
    //    @Value("${redis.maxIdle}")
    //    private int redisMaxIdle;
    //    @Value("${redis.maxTotal}")
    //    private int redisMaxTotal;
    //    @Value("${redis.maxWait}")
    //    private int redisMaxWaitMillis;
    //    @Value("${redis.timeout:5000}")
    //    private int redisTimeout;
    //    @Value("${redis.testOnBorrow}")
    //    private boolean redisTestOnBorrow;
    //    @Value("${redis.database:2}")
    //    private int redisCacheDatabase;


    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbTestQuery() {
        return dbTestQuery;
    }

    public void setDbTestQuery(String dbTestQuery) {
        this.dbTestQuery = dbTestQuery;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getMongoReplicaSet() {
        return mongoReplicaSet;
    }

    public void setMongoReplicaSet(String mongoReplicaSet) {
        this.mongoReplicaSet = mongoReplicaSet;
    }

    public String getMongoDbName() {
        return mongoDbName;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    public String getMongoUsername() {
        return mongoUsername;
    }

    public void setMongoUsername(String mongoUsername) {
        this.mongoUsername = mongoUsername;
    }

    public String getMongoPassword() {
        return mongoPassword;
    }

    public void setMongoPassword(String mongoPassword) {
        this.mongoPassword = mongoPassword;
    }

    public int getMongoConnectionsPerHost() {
        return mongoConnectionsPerHost;
    }

    public void setMongoConnectionsPerHost(int mongoConnectionsPerHost) {
        this.mongoConnectionsPerHost = mongoConnectionsPerHost;
    }

    public int getMongoThreadsAllowedToBlockForConnectionMultiplier() {
        return mongoThreadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMongoThreadsAllowedToBlockForConnectionMultiplier(int mongoThreadsAllowedToBlockForConnectionMultiplier) {
        this.mongoThreadsAllowedToBlockForConnectionMultiplier = mongoThreadsAllowedToBlockForConnectionMultiplier;
    }

    public int getMongoConnectTimeout() {
        return mongoConnectTimeout;
    }

    public void setMongoConnectTimeout(int mongoConnectTimeout) {
        this.mongoConnectTimeout = mongoConnectTimeout;
    }

    public int getMongoMaxWaitTime() {
        return mongoMaxWaitTime;
    }

    public void setMongoMaxWaitTime(int mongoMaxWaitTime) {
        this.mongoMaxWaitTime = mongoMaxWaitTime;
    }

    public boolean isMongoSocketKeepAlive() {
        return mongoSocketKeepAlive;
    }

    public void setMongoSocketKeepAlive(boolean mongoSocketKeepAlive) {
        this.mongoSocketKeepAlive = mongoSocketKeepAlive;
    }

    public int getMongoSocketTimeout() {
        return mongoSocketTimeout;
    }

    public void setMongoSocketTimeout(int mongoSocketTimeout) {
        this.mongoSocketTimeout = mongoSocketTimeout;
    }

    public String getRabbitMQAddresses() {
        return rabbitMQAddresses;
    }

    public void setRabbitMQAddresses(String rabbitMQAddresses) {
        this.rabbitMQAddresses = rabbitMQAddresses;
    }

    public String getRabbitMQUsername() {
        return rabbitMQUsername;
    }

    public void setRabbitMQUsername(String rabbitMQUsername) {
        this.rabbitMQUsername = rabbitMQUsername;
    }

    public String getRabbitMQPassword() {
        return rabbitMQPassword;
    }

    public void setRabbitMQPassword(String rabbitMQPassword) {
        this.rabbitMQPassword = rabbitMQPassword;
    }

    public String getRabbitMQQueue() {
        return rabbitMQQueue;
    }

    public void setRabbitMQQueue(String rabbitMQQueue) {
        this.rabbitMQQueue = rabbitMQQueue;
    }

    public String getRabbitMQExchange() {
        return rabbitMQExchange;
    }

    public void setRabbitMQExchange(String rabbitMQExchange) {
        this.rabbitMQExchange = rabbitMQExchange;
    }
}
