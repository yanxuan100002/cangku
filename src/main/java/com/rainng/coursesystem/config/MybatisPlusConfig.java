package com.rainng.coursesystem.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//这个MybatisPlusConfig类是一个Spring配置类，它专门用于配置MyBatis Plus相关的设置
@MapperScan("com.rainng.coursesystem.dao.mapper")
@Configuration
public class MybatisPlusConfig {
    @Bean

    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}