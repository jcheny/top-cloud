package com.ihave.factory;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/19 下午2:37
 */
@ConfigurationProperties(prefix = "social")
public class SocialProperties {
    private Boolean enabled = false;
    private String domain;
    private Map<AuthDefaultSource, AuthConfig> oauth = new HashMap<>();
    private Map<String, String> alias = new HashMap<>();

    public SocialProperties() {
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public String getDomain() {
        return this.domain;
    }

    public Map<AuthDefaultSource, AuthConfig> getOauth() {
        return this.oauth;
    }

    public Map<String, String> getAlias() {
        return this.alias;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }

    public void setOauth(final Map<AuthDefaultSource, AuthConfig> oauth) {
        this.oauth = oauth;
    }

    public void setAlias(final Map<String, String> alias) {
        this.alias = alias;
    }
}