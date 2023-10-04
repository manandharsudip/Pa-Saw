package com.cotiviti.Pasaw.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

  private final JwtProperties properties;

  // so here just token is decoded and plain information is returned to required place.

  public DecodedJWT decode(String token) {
    return JWT
      .require(Algorithm.HMAC256(properties.getSecretKey()))
      .build()
      .verify(token);
  }
}
