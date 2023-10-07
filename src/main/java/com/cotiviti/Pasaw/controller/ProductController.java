package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.ProductDto;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.ProductService;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductEntity>> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductEntity> getProductById(
    @PathVariable("productId") Long product
  ) {
    return productService.getProdutById(product);
  }

  @GetMapping("/category/{catId}")
  public ResponseEntity<List<ProductEntity>> getProductByCategoryId(
    @PathVariable("catId") Long catId
  ) {
    return productService.getProductByCategoryId(catId);
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addNewProduct(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    // @RequestBody ProductEntity product,
    @ModelAttribute ProductDto productDto
  ) throws IOException {
    return productService.addProduct(userPrincipal, productDto);
  }

  @PutMapping("/update/{productId}")
  public ResponseEntity<HttpStatus> updateProductById(
    @PathVariable("productId") Long productId,
    @RequestBody ProductEntity product
  ) {
    return productService.updateProductById(productId, product);
  }

  @DeleteMapping("/delete/{productId}")
  public ResponseEntity<HttpStatus> deleteProductById(
    @PathVariable("productId") Long productId
  ) {
    return productService.deleteProductById(productId);
  }
}
