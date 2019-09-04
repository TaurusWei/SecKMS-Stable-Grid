package com.sansec.kms.config;

import klms.db.DbOper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WeiBingtao/13156050650@163.com
 * @Version: 1.0
 * @Description:数据库相关配置
 * @Date: 2019/8/20 17:02
 */
@Configuration
public class KmsDBConfig {
    @Value("${database.dialect}")
    String  dialect;
    @Value("${database.driver_class}")
    String  driver_class;
    @Value("${database.provider_class}")
    String  provider_class;
    @Value("${database.url}")
    String  url;
    @Value("${database.username}")
    String  username;
    @Value("${database.password}")
    String  password;
    @Value("${database.initialSize}")
    String  initialSize;
    @Value("${database.minIdle}")
    String  minIdle;
    @Value("${database.maxActive}")
    String  maxActive;
    @Value("${database.maxWait}")
    String  maxWait;
    @Value("${database.timeBetweenEvictionRunsMillis}")
    String  timeBetweenEvictionRunsMillis;
    @Value("${database.minEvictableIdleTimeMillis}")
    String  minEvictableIdleTimeMillis;
    @Value("${database.validationQuery}")
    String  validationQuery;
    @Value("${database.testWhileIdle}")
    String  testWhileIdle;
    @Value("${database.testOnBorrow}")
    String  testOnBorrow;
    @Value("${database.testOnReturn}")
    String  testOnReturn;
    @Value("${database.poolPreparedStatements}")
    String  poolPreparedStatements;
    @Value("${database.maxPoolPreparedStatementPerConnectionSize}")
    String  maxPoolPreparedStatementPerConnectionSize;
    @Value("${database.removeAbandoned}")
    String  removeAbandoned;
    @Value("${database.removeAbandonedTimeout}")
    String  removeAbandonedTimeout;
    @Value("${database.logAbandoned}")
    String  logAbandoned;
    @Bean
    public DbOper KmsDBObjectManager(){
        Map properties = new HashMap();
        properties.put("hibernate.dialect",dialect);
        properties.put("hibernate.connection.driver_class",driver_class);
        properties.put("hibernate.connection.provider_class",provider_class);
        properties.put("url",url);
        properties.put("username",username);
        properties.put("password",password);
        properties.put("initialSize",initialSize);
        properties.put("minIdle",minIdle);
        properties.put("maxActive",maxActive);
        properties.put("maxWait",maxWait);
        properties.put("timeBetweenEvictionRunsMillis",timeBetweenEvictionRunsMillis);
        properties.put("minEvictableIdleTimeMillis",minEvictableIdleTimeMillis);
        return new DbOper(properties);
    }
}
