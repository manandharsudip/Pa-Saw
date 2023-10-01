package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.JwtIssuer;
import com.cotiviti.Pasaw.security.UserPrincipal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtIssuer jwtIssuer; // injection assisted by @RequiredArgsConstructor: similar to creating a constructor and initializing things
  private final AuthenticationManager authenticationManager;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    var authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    var principal = (UserPrincipal) authentication.getPrincipal();

    var roles = principal
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    var token = jwtIssuer.issue(
      principal.getUserId(),
      principal.getUsername(),
      roles
    );
    return LoginResponse.builder().accessToken(token).build();
  }
}
