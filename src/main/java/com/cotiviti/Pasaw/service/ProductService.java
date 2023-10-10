package com.cotiviti.Pasaw.service;

import com.cotiviti.Pasaw.dto.ProductDto;
import com.cotiviti.Pasaw.dto.ProductResponseDto;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ProductService {
  ResponseEntity<HttpStatus> addProduct(
    UserPrincipal principal,
    // ProductEntity product,
    ProductDto productDto
  ) throws IOException;

  ResponseEntity<List<ProductResponseDto>> getAllProducts();

  ResponseEntity<ProductResponseDto> getProdutById(Long catId);

  ResponseEntity<List<ProductResponseDto>> getProductByCategoryId(Long catId);

  ResponseEntity<HttpStatus> updateProductById(
    Long productId,
    ProductEntity product
  );

  ResponseEntity<HttpStatus> deleteProductById(Long productId);
}
