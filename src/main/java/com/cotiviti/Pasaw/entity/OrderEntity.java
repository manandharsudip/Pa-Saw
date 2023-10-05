// package com.cotiviti.Pasaw.entity;

// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import com.cotiviti.Pasaw.model.OrderStatus;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
// @Entity
// @Table(name = "order")
// public class OrderEntity {

//   @Id
//   @GeneratedValue(strategy = GenerationType.AUTO)
//   private Long id;

//   // @ManyToOne(fetch = FetchType.EAGER, optional = false)
//   // private UserEntity userEntity;
//   // @JoinColumn(name = "User_ID", nullable = false)
//   // delete on cascade ?
//   // @JsonIgnore

//   // @ManyToOne(fetch = FetchType.EAGER, optional = false)
//   // private ProductEntity productEntity;
//   // @JoinColumn(name = "Product_ID", nullable = false)
//   // delete on cascade ?
//   // @JsonIgnore

//   @Enumerated(EnumType.ORDINAL)
//   private OrderStatus status;
// }
