package com.project.smallbeginjava11.oauth.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
public class GoogleOAuthProperties {
    private String clientId;
    private String clientSecret;
    private String scope;
    private String responseType;
    private String prompt;
    private String accessType;
    private String redirectUri;
    private String grantType;

}
