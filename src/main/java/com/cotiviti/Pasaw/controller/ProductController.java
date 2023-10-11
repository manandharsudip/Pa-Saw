package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.ProductDto;
import com.cotiviti.Pasaw.dto.ProductResponseDto;
import com.cotiviti.Pasaw.dto.TestDTO;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.model.LoginRequest;
import com.cotiviti.Pasaw.model.LoginResponse;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.ProductService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/product")
public class ProductController {

  private final ProductService productService;

  @PostMapping("/login")
  // public LoginResponse login(@RequestParam("categoryname") String categoryname, @RequestParam("file") MultipartFile file) {
  public LoginResponse login(@ModelAttribute TestDTO tes) {
    System.out.println("My Email: " + tes.getCategoryname());
    System.out.println("My Email: " + tes.getDescription());
    System.out.println("My Email: " + tes.getImageurl());
    return null;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
    try {
      return productService.getAllProducts();
      // return null;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> getProductById(
    @PathVariable("productId") Long product
  ) {
    try {
      return productService.getProdutById(product);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/category/{catId}")
  public ResponseEntity<List<ProductResponseDto>> getProductByCategoryId(
    @PathVariable("catId") Long catId
  ) {
    try {
      return productService.getProductByCategoryId(catId);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addNewProduct(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    // @RequestBody ProductEntity product,
    @ModelAttribute ProductDto productDto
  ) throws IOException {
    try {
      System.out.println("My Email: " + productDto.getProductname());
      System.out.println("My Email: " + productDto.getDescription());
      System.out.println("My Email: " + productDto.getImageurl());
      
      return productService.addProduct(userPrincipal, productDto);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{productId}")
  public ResponseEntity<HttpStatus> updateProductById(
    @PathVariable("productId") Long productId,
    @ModelAttribute ProductDto productDto
  ) throws IOException {
    // try {
      System.out.println("My Email: " + productDto.getProductname());
      System.out.println("My Email: " + productId);
      System.out.println("My Email: " + productDto.getImageurl());
      return productService.updateProductById(productId, productDto);
    // } catch (Exception e) {
    //   return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    // }
  }

  @DeleteMapping("/delete/{productId}")
  public ResponseEntity<HttpStatus> deleteProductById(
    @PathVariable("productId") Long productId
  ) {
    try {
      return productService.deleteProductById(productId);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
}
