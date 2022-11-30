package com.project.smallbeginjava11.oauth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
public class GoogleOAuthProperties {
    private String clientId;
    private String clientSecret;
    private String scope;
    private String responseType;
    private String prompt;
    private String redirectUri;
    private String grantType;

}
