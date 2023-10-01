package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.entity.UserEntity;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final String EXISTING_EMAIL = "admin@mstech.com";
  private static final String ANOTHER_EMAIL = "user@mstech.com";
  private static final String CUSTOMER_EMAIL = "customer@mstech.com";

  @Override
  public Optional<UserEntity> findByEmail(String email) {
    if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setId(1L);
      user.setEmail(EXISTING_EMAIL);
      user.setPassword(
        "$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"
      ); // pass
      user.setRole("ROLE_ADMIN");
      return Optional.of(user);
    } else if (ANOTHER_EMAIL.equals(email)) {
      var user = new UserEntity();
      user.setId(10L);
      user.setEmail(ANOTHER_EMAIL);
      user.setPassword(
        "$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"
      ); // pass
      user.setRole("ROLE_USER");
      return Optional.of(user);
    } else if (CUSTOMER_EMAIL.equals(email)) {
      var user = new UserEntity();
      user.setId(20L);
      user.setEmail(CUSTOMER_EMAIL);
      user.setPassword(
        "$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"
      ); // pass
      user.setRole("ROLE_CUSTOMER");
      return Optional.of(user);
    }

    return Optional.empty();
  }
}
