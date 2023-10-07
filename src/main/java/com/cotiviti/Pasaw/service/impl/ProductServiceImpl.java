package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.ProductDto;
import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.entity.ProductEntity;
import com.cotiviti.Pasaw.functions.CustomFunction;
import com.cotiviti.Pasaw.repository.CategoryRepository;
// import com.cotiviti.Pasaw.model.Status;
import com.cotiviti.Pasaw.repository.ProductRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.ProductService;
import java.io.IOException;
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
      .orElseThrow();


    // path, file and upload function
    String uploadDir =
      "public/images/Products/" + categoryEntity.getCategoryname();
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
  public ResponseEntity<List<ProductEntity>> getAllProducts() {
    List<ProductEntity> products = productRepository.findAll();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ProductEntity>> getProductByCategoryId(
    Long catId
  ) {
    List<ProductEntity> products = productRepository.findByCategoryid(catId);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ProductEntity> getProdutById(Long productId) {
    ProductEntity product = productRepository.findById(productId).orElseThrow();
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<HttpStatus> updateProductById(
    Long productId,
    ProductEntity product
  ) {
    ProductEntity oldProduct = productRepository
      .findById(productId)
      .orElseThrow();

    String productname = product.getProductname();
    Long categoryid = product.getCategoryid();
    String description = product.getDescription();
    String imageurl = product.getImageurl();
    Float price = product.getPrice();

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
      imageurl != null && !Objects.equals(imageurl, oldProduct.getImageurl())
    ) {
      oldProduct.setImageurl(imageurl);
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

    if (exists) {
      productRepository.deleteById(productId);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
