package com.cotiviti.Pasaw.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // what is this?
@EnableWebSecurity // what is this?
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // rest API should be stateless, that is what this configuration does
      )
      .formLogin(form -> form.disable())
      .headers(headers -> headers.frameOptions().disable())
      .authorizeHttpRequests(auth ->
        auth
          .antMatchers("/")
          .permitAll()
          .antMatchers("/auth/login")
          .permitAll()
          .requestMatchers(toH2Console())
          .permitAll()
          .anyRequest().authenticated()
      );
    return http.build();
  }
}