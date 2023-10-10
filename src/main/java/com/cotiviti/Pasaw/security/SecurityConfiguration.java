package com.cotiviti.Pasaw.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // what is this?
@EnableWebSecurity // what is this?
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomUserDetailService customUserDetailService;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedMethods("GET", "PUT", "POST", "DELETE")
          .allowedHeaders("*")
          .allowedOriginPatterns("*")
          .allowCredentials(true);
      }
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http.addFilterBefore(
      jwtAuthenticationFilter,
      UsernamePasswordAuthenticationFilter.class
    );
    http.cors();
    http
      // .cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // rest API should be stateless, that is what this configuration does
      )
      .formLogin(form -> form.disable())
      .headers(headers -> headers.frameOptions().disable())
      .authorizeHttpRequests(auth ->
        auth
          .antMatchers(
            "/",
            "/auth/login",
            "/login",
            "/register",
            "/notsecured",
            "/api/ems/product/**",
            "/api/ems/category/**"
          )
          .permitAll()
          .antMatchers("/customer", "/api/ems/cart/**", "/myInfo")
          .hasRole("CUSTOMER")
          .antMatchers("/admin", "/api/ems/order/**")
          .hasRole("ADMIN")
          .antMatchers("/user")
          .hasAnyRole("ADMIN", "USER")
          .requestMatchers(toH2Console())
          .permitAll()  
          .anyRequest()
          .authenticated()
      );
    return http.build();
  }

  // this will make sure password wont be plain text, actually bycrypt
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http)
    throws Exception {
    return http
      .getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(customUserDetailService)
      .passwordEncoder(passwordEncoder())
      .and()
      .build();
  }
}
