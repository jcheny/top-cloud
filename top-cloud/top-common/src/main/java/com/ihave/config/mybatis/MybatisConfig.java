package com.ihave.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chen
 * @Date: 2021/4/16 9:54 AM
 * @Version 1.0
 */
@Configuration
@MapperScan("com.ihave.mapper")
public class MybatisConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }

    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * Id 生成器-->
     * <p>
     * 特殊的一些类使用
     * 默认使用
     *
     * @return
     */
    @Bean
    public IKeyGenerator iKeyGenerator() {
        return new H2KeyGenerator();
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig config = new GlobalConfig.DbConfig();
        config.setLogicDeleteField("is_deleted");
        config.setLogicDeleteValue("1");
        config.setLogicNotDeleteValue("0");
        globalConfig.setDbConfig(config);
        return globalConfig;
    }

}
