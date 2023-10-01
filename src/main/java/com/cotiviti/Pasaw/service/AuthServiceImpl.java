package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.JwtIssuer;
import com.cotiviti.Pasaw.security.UserPrincipal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtIssuer jwtIssuer; // injection assisted by @RequiredArgsConstructor: similar to creating a constructor and initializing things
  private final AuthenticationManager authenticationManager;

  @Override
  public LoginResponse loginAttempt(String email, String password) {
    var authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        email,
        password
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
