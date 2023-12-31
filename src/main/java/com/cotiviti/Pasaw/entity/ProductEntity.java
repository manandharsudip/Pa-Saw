package com.cotiviti.Pasaw.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cotiviti.Pasaw.model.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Cacheable
@Table(name = "products")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String productname;

  private Long categoryid;

  @Column(columnDefinition = "TEXT")
  private String description;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "added_by", nullable = false)
  // delete on cascade ?
  // @JsonIgnore
  private UserEntity userEntity;

  private Float price;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  private String imageurl;

  private Date created_date;

  private Date updated_date;
}


