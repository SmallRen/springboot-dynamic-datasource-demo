package com.smallren.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 于数据源配置，参考SpringBoot官方文档第79章《Data Access》
 * @Author: renbaojia
 * @CreateDate: 2019/8/8 13:56
 * @UpdateUser:
 * @UpdateDate: 2019/8/8 13:56
 * @UpdateRemark:
 */
@Configuration
public class DataSourceConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 加载配置文件的属性
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        LOGGER.info("init master data source---------");
        return DataSourceBuilder.create().build();
    }

    /**
     * 加载配置文件的属性
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        LOGGER.info("init slave data source-----------");
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        //设置数据源
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE, slaveDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        // 设置默认的数据源 方法不再切面上的时候使用默认的数据源
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }

}
