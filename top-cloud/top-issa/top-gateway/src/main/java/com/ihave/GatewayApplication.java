package com.ihave;

import com.ihave.service.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MySource.class})
public class GatewayApplication {

    /**
     * 如果需要启动sentinel控制台
     * 请使用docker运行
     * 命令：docker run --name sentinel -d  -p 8858:8858  bladex/sentinel-dashboard:1.7.1
     * 这个命令的端口为8858，和配置文件一致
     * 控制台的版本为1.7.1
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
