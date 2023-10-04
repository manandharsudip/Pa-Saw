package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ProductService {
  ResponseEntity<HttpStatus> addProduct(
    UserPrincipal principal,
    ProductEntity product
  );

  ResponseEntity<List<ProductEntity>> getAllProducts();

  ResponseEntity<ProductEntity> getProdutById(Long catId);

  ResponseEntity<List<ProductEntity>> getProductByCategoryId(Long catId);

  ResponseEntity<HttpStatus> updateProductById(
    Long productId,
    ProductEntity product
  );

  ResponseEntity<HttpStatus> deleteProductById(Long productId);
}
