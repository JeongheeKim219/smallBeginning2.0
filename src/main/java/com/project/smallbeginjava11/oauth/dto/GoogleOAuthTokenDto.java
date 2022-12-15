package com.project.smallbeginjava11.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleOAuthTokenDto {

    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String idToken;

    @Builder
    public GoogleOAuthTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
