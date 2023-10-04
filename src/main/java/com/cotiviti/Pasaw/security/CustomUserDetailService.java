package com.cotiviti.Pasaw.security;

import com.cotiviti.Pasaw.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    // Spring uses this method to load user data from database...
    var user = userService.findByEmail(username).orElseThrow();
    // later we can fetch this throgh databse: repository

    // As UserPrincipal is implemented from UserDetails
    return UserPrincipal
      .builder()
      .userId(user.getId())
      .email(user.getEmail())
      .password(user.getPassword())
      .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
      .build();
  }
}
