package com.ihave.config.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: chen
 * @Date: 2021/4/16 9:21 AM
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
//SwaggerProperties  里面的属性的值从配置文件中获取
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

    private SwaggerProperties swaggerProperties;

    //使用构造方法的形式去注入SwaggerProperties到本类中
    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    //swagger2  必须注册的bean
    @Bean
    public Docket config() {
        //选择使用swagger2文档
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //配置文档的信息
                .apiInfo(setApiInfo())
                //配置选择器
                .select()
                //选择那些包 --》com.chen.controller
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                //选择那些路径下的包  - 》 any
                .paths(PathSelectors.any())
                //构建
                .build();
        docket.securityContexts(securityContexts());
        docket.securitySchemes(securitySchemes());
        return docket;
    }

    //配置api文档的基础信息
    private ApiInfo setApiInfo() {
        return new ApiInfoBuilder().contact(
                new Contact(swaggerProperties.getName(), swaggerProperties.getUrl(), swaggerProperties.getEmail())
        )
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();
    }

    /**
     * 安全的规则配置
     *
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        return Arrays.asList(new ApiKey("Authorization", "Authorization", "Authorization"));
    }

    /**
     * 安全的上下问
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(new SecurityContext(
                Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessResource")})),
                PathSelectors.any()
        ));
    }
}
