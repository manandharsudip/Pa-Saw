package com.cotiviti.Pasaw.repository;

import com.cotiviti.Pasaw.entity.ProductEntity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
  List<ProductEntity> findByCategoryid(Long categoryid);
  // ProductEntity findByProductEntity(ProductEntity productEntity);
}
