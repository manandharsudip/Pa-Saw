package com.cotiviti.Pasaw.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JwtIssuer {



  private final JwtProperties jwtProperties;

    // Issues a jwt token based on used data and adds some expiry time to token
    // create method is used to build a new token
    // needs to be sign using some sort of secret key

  public String issue(long userId, String email, List<String> roles) {
    return JWT
      .create()
      .withSubject(String.valueOf(userId))
      .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
      .withClaim("e", email)
      .withClaim("a", roles)
      .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
  }
}
