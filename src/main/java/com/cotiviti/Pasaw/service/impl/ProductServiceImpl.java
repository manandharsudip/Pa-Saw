package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.ProductDto;
import com.cotiviti.Pasaw.dto.ProductResponseDto;
import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.exception.ResourceNotFoundException;
import com.cotiviti.Pasaw.functions.CustomFunction;
import com.cotiviti.Pasaw.repository.CategoryRepository;
// import com.cotiviti.Pasaw.model.Status;
import com.cotiviti.Pasaw.repository.ProductRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.ProductService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final CustomFunction customFunction;

  @Override
  public ResponseEntity<HttpStatus> addProduct(
    UserPrincipal principal,
    // ProductEntity product,
    ProductDto productDto
  ) throws IOException {
    CategoryEntity categoryEntity = categoryRepository
      .findById(productDto.getCategoryid())
      .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

    // path, file and upload function
    String uploadDir =
      "D:/Final Project Java/pasaw-ui/src/assets/images/Products/" +
      categoryEntity.getCategoryname();
    // String uploadDir =
    //   "public/images/Products/" + categoryEntity.getCategoryname();
    MultipartFile imageFile = productDto.getImageurl();
    String filename = customFunction.uploadFunction(uploadDir, imageFile);

    ProductEntity productEntity = new ProductEntity();

    productEntity.setProductname(productDto.getProductname());
    productEntity.setDescription(productDto.getDescription());
    productEntity.setCategoryid(productDto.getCategoryid());
    productEntity.setPrice(productDto.getPrice());
    productEntity.setStatus(productDto.getStatus());

    // productEntity.setStatus(Status.COMING_SOON);
    if (filename != null && !filename.isEmpty()) {
      productEntity.setImageurl(filename);
    }

    productEntity.setCreated_date(new Date());
    productEntity.setUpdated_date(new Date());

    userRepository
      .findByEmail(principal.getEmail())
      .map(user -> {
        productEntity.setUserEntity(user);
        return productRepository.save(productEntity);
      });
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
    List<ProductEntity> products = productRepository.findAll();

    List<ProductResponseDto> reponseProduct = new ArrayList<>();
    for (ProductEntity product : products) {
      ProductResponseDto dto = new ProductResponseDto();
      dto.setProdutId(product.getId());
      dto.setProductname(product.getProductname());
      dto.setCategoryid(product.getCategoryid());
      dto.setCategoryname(
        categoryRepository
          .findById(product.getCategoryid())
          .orElseThrow()
          .getCategoryname()
      );
      dto.setDescription(product.getDescription());
      dto.setUserId(product.getUserEntity().getId());
      dto.setPrice(product.getPrice());
      dto.setImageurl(product.getImageurl());
      dto.setStatus(product.getStatus());
      reponseProduct.add(dto);
    }

    return new ResponseEntity<>(reponseProduct, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ProductResponseDto>> getProductByCategoryId(
    Long catId
  ) {
    List<ProductEntity> products = productRepository.findByCategoryid(catId);

    List<ProductResponseDto> reponseProduct = new ArrayList<>();
    for (ProductEntity product : products) {
      ProductResponseDto dto = new ProductResponseDto();
      dto.setProdutId(product.getId());
      dto.setProductname(product.getProductname());
      dto.setCategoryid(product.getCategoryid());
      dto.setCategoryname(
        categoryRepository
          .findById(product.getCategoryid())
          .orElseThrow()
          .getCategoryname()
      );
      dto.setDescription(product.getDescription());
      dto.setUserId(product.getUserEntity().getId());
      dto.setPrice(product.getPrice());
      dto.setImageurl(product.getImageurl());
      dto.setStatus(product.getStatus());
      reponseProduct.add(dto);
    }

    return new ResponseEntity<>(reponseProduct, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ProductResponseDto> getProdutById(Long productId) {
    ProductEntity product = productRepository
      .findById(productId)
      .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

    ProductResponseDto productDto = new ProductResponseDto();
    productDto.setProdutId(product.getId());
    productDto.setProductname(product.getProductname());
    productDto.setCategoryname(
      categoryRepository
        .findById(product.getCategoryid())
        .orElseThrow()
        .getCategoryname()
    );
    productDto.setDescription(product.getDescription());
    productDto.setUserId(product.getUserEntity().getId());
    productDto.setPrice(product.getPrice());
    productDto.setImageurl(product.getImageurl());
    productDto.setStatus(product.getStatus());

    return new ResponseEntity<>(productDto, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<HttpStatus> updateProductById(
    Long productId,
    ProductDto productDto
  ) throws IOException {
    CategoryEntity categoryEntity = categoryRepository
      .findById(productDto.getCategoryid())
      .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

      System.out.println("My Email: " + productDto.getProductname());
      System.out.println("My Email: " + productId);
      System.out.println("My Email: " + productDto.getImageurl());

    // path, file and upload function
    String uploadDir =
      "D:/Final Project Java/pasaw-ui/src/assets/images/Products/" +
      categoryEntity.getCategoryname();
    MultipartFile imageFile = productDto.getImageurl();
    String filename = customFunction.uploadFunction(uploadDir, imageFile);

    ProductEntity oldProduct = productRepository
      .findById(productId)
      .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

    String productname = productDto.getProductname();
    Long categoryid = productDto.getCategoryid();
    String description = productDto.getDescription();
    Float price = productDto.getPrice();

    if (
      productname != null &&
      !Objects.equals(productname, oldProduct.getProductname())
    ) {
      oldProduct.setProductname(productname);
    }

    if (
      categoryid != null &&
      !Objects.equals(categoryid, oldProduct.getCategoryid())
    ) {
      oldProduct.setCategoryid(categoryid);
    }

    if (
      description != null &&
      !Objects.equals(description, oldProduct.getDescription())
    ) {
      oldProduct.setDescription(description);
    }

    if (
      filename != null &&
      !Objects.equals(filename, oldProduct.getImageurl()) &&
      !filename.isEmpty()
    ) {
      oldProduct.setImageurl(filename);
    }

    if (price != null && !Objects.equals(price, oldProduct.getPrice())) {
      oldProduct.setPrice(price);
    }

    oldProduct.setUpdated_date(new Date());
    productRepository.save(oldProduct);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteProductById(Long productId) {
    boolean exists = productRepository.existsById(productId);

    if (!exists) {
      throw new ResourceNotFoundException("Product Not Found");
    }
    productRepository.deleteById(productId);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
