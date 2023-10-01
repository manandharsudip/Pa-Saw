package com.cotiviti.Pasaw.security;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtDecoder jwtDecoder;
  private final JwtToPrincipalConverter jwtToPrincipalConverter;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    extractTokenFromRequest(request)
      .map(jwtDecoder::decode) // this is same as .map(string -> jwtDecoder.decode(str)) - method reference
      .map(jwtToPrincipalConverter::convert)
      .map(UserPrincipalAuthenticationToken::new) // User Principal must be wrapped to UserPrincipalAuthToken
      .ifPresent(auth ->
        SecurityContextHolder.getContext().setAuthentication(auth)
      );
    filterChain.doFilter(request, response);
  }

  // optional because token can either be null or actually a (valid or invalid) token
  private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
      return Optional.of(token.substring(7));
    }
    return Optional.empty();
  }
}
