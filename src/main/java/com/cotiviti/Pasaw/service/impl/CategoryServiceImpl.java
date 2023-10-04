package com.cotiviti.Pasaw.service.impl;

import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.repository.CategoryRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CategoryService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<HttpStatus> addCategory(
    UserPrincipal principal,
    CategoryEntity category
  ) {
    userRepository
      .findByEmail(principal.getEmail())
      .map(user -> {
        category.setUserEntity(user);
        category.setCreated_date(new Date());
        category.setUpdated_date(new Date());
        return categoryRepository.save(category);
      });

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<CategoryEntity>> getAllCategory() {
    List<CategoryEntity> cats = categoryRepository.findAll();
    return new ResponseEntity<>(cats, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CategoryEntity> getCategoryById(Long catId) {
    CategoryEntity cat = categoryRepository.findById(catId).orElseThrow();
    return new ResponseEntity<>(cat, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<HttpStatus> updateCategory(
    Long catId,
    CategoryEntity category
  ) {
    CategoryEntity oldCat = categoryRepository.findById(catId).orElseThrow();

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

    if (exists) {
      categoryRepository.deleteById(catId);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
