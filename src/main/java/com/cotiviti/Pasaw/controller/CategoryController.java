package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/category")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryEntity>> allCategories() {
    return categoryService.getAllCategory();
  }

  @GetMapping("/{catId}")
  public ResponseEntity<CategoryEntity> getCategoryById(
    @PathVariable("catId") Long catId
  ) {
    return categoryService.getCategoryById(catId);
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addNewCategory(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody CategoryEntity category
  ) {
    return categoryService.addCategory(userPrincipal, category);
  }

  @PutMapping("/update/{catId}")
  public ResponseEntity<HttpStatus> updateCategory(
    @PathVariable("catId") Long catId,
    @RequestBody CategoryEntity category
  ) {
    return categoryService.updateCategory(catId, category);
  }

  @DeleteMapping("/delete/{catId}")
  public ResponseEntity<HttpStatus> deleteCategoryById(
    @PathVariable("catId") Long catId
  ) {
    return categoryService.deleteCategoryById(catId);
  }
}
