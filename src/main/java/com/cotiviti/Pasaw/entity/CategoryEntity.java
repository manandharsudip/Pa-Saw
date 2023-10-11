package com.cotiviti.Pasaw.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Cacheable
@Table(name = "category")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String categoryname;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String imageurl;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "added_by", nullable = false)
  // delete on cascade ?
  // @JsonIgnore
  private UserEntity userEntity;

  private Date created_date;

  private Date updated_date;
}
