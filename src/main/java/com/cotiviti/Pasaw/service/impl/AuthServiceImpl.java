package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.JwtIssuer;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.AuthService;

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

    // this above line is enough for authentication...

    System.out.println("Authentication Obj: "+ authentication);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    var principal = (UserPrincipal) authentication.getPrincipal();

    var roles = principal
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    // System.out.println("Roles: "+ roles.contains("ROLE_ADMIN"));

    var token = jwtIssuer.issue(
      principal.getUserId(),
      principal.getUsername(),
      roles
    );

    // if (roles.contains(myRole)){
    //   return LoginResponse.builder().accessToken(token).build();
    // }

    return LoginResponse.builder().accessToken(token).build();

  }
}
