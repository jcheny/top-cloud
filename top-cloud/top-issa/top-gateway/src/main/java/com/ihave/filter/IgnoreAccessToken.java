package com.ihave.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/27 下午4:00
 */
@Data
@ConfigurationProperties(prefix = "top.gateway.ignore")
public class IgnoreAccessToken {

    private List<String> allowPaths;


}
