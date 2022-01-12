package com.ihave.config.file;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/4 下午5:27
 */
@Configuration
public class MultipartConfig {

    public static final long MAX_FILE_SIZE = 120;

    public static final long MAX_REQUEST_SIZE = 1200;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(MAX_FILE_SIZE)); //MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(MAX_REQUEST_SIZE));
        // 设置文件路径
        return factory.createMultipartConfig();
    }
}
