package com.cotiviti.Pasaw.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    // when ever a user makes a successjful login a jwt token is issue from here
    private final String accessToken;
}
