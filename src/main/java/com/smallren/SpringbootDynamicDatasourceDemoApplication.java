package com.smallren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

//动态数据源排除单一的数据源配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.smallren.mapper")
@EnableCaching
public class SpringbootDynamicDatasourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamicDatasourceDemoApplication.class, args);
    }

}
