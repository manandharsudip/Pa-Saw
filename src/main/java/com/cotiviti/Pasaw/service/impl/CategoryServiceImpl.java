package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.dto.CategoryDto;
import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.exception.ResourceNotFoundException;
import com.cotiviti.Pasaw.functions.CustomFunction;
import com.cotiviti.Pasaw.repository.CategoryRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;
  private final CustomFunction customFunction;

  @Override
  public ResponseEntity<HttpStatus> addCategory(
    UserPrincipal principal,
    CategoryDto categoryDto
  ) throws IOException {
    String uploadDir =
      "public/images/CategoryImage/" + categoryDto.getCategoryname();
    MultipartFile imageFile = categoryDto.getImageurl();
    String filename = customFunction.uploadFunction(uploadDir, imageFile);

    CategoryEntity categoryEntity = new CategoryEntity();

    if (filename != null && !filename.isEmpty()) {
      categoryEntity.setImageurl(filename);
    }

    categoryEntity.setCategoryname(categoryDto.getCategoryname());
    categoryEntity.setDescription(categoryDto.getDescription());
    categoryEntity.setCreated_date(new Date());
    categoryEntity.setUpdated_date(new Date());

    userRepository
      .findByEmail(principal.getEmail())
      .map(user -> {
        categoryEntity.setUserEntity(user);
        return categoryRepository.save(categoryEntity);
      })
      .orElseThrow(() ->
        new ResourceNotFoundException("Could not set user entity")
      );

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<CategoryEntity>> getAllCategory() {
    try {
      List<CategoryEntity> cats = categoryRepository.findAll();
      return new ResponseEntity<>(cats, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<CategoryEntity> getCategoryById(Long catId) {
    CategoryEntity cat = categoryRepository
      .findById(catId)
      .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
    return new ResponseEntity<>(cat, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<HttpStatus> updateCategory(
    Long catId,
    CategoryEntity category
  ) {
    CategoryEntity oldCat = categoryRepository
      .findById(catId)
      .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

    String catname = category.getCategoryname();
    String description = category.getDescription();
    String imageurl = category.getImageurl();

    if (catname != null && !Objects.equals(catname, oldCat.getCategoryname())) {
      oldCat.setCategoryname(catname);
    }

    if (
      description != null &&
      !Objects.equals(description, oldCat.getDescription())
    ) {
      oldCat.setDescription(description);
    }

    if (imageurl != null && !Objects.equals(imageurl, oldCat.getImageurl())) {
      oldCat.setImageurl(imageurl);
    }

    oldCat.setUpdated_date(new Date());
    categoryRepository.save(oldCat);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteCategoryById(Long catId) {
    boolean exists = categoryRepository.existsById(catId);

    if (!exists) {
      throw new ResourceNotFoundException("Category Not Found");
    }
    categoryRepository.deleteById(catId);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
