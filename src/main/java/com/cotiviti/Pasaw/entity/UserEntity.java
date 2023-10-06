package com.cotiviti.Pasaw.entity;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  private String firstname;
  private String lastname;

  // @JsonIgnore
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  private Date created_date;

  private Date updated_date;
}
