package com.ihave.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @Author: chen
 * @Date: 2021/4/16 9:26 AM
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties implements Serializable {

    /**
     * 包扫描的路径
     */
    private String basePackage;

    /**
     * 联系人的名称
     */
    private String name;

    /**
     * 联系人的主页
     */
    private String url;

    /**
     * 联系人的邮箱
     */
    private String email;

    /**
     * API的标题
     */
    private String title;

    /**
     * API的描述
     */
    private String description;

    /**
     * API的版本号
     */
    private String version;

    /**
     * API的服务团队
     */
    private String termsOfServiceUrl;


}
