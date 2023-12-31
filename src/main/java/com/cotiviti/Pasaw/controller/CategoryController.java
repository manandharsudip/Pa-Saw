package com.cotiviti.Pasaw.controller;

import com.cotiviti.Pasaw.dto.CategoryDto;
import com.cotiviti.Pasaw.dto.CategoryResponseDto;
import com.cotiviti.Pasaw.dto.TestDTO;
import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CategoryService;
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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ems/category")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
    try {
      return categoryService.getAllCategory();
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{catId}")
  public ResponseEntity<CategoryEntity> getCategoryById(
    @PathVariable("catId") Long catId
  ) {
    try {
      return categoryService.getCategoryById(catId);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addNewCategory(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @RequestBody TestDTO categoryDto
  ) throws IOException {
    
    System.out.println("Category Name: "+ categoryDto.getCategoryname());
    // System.out.println("Category DTo: "+ categoryDto.getImageurl());
    System.out.println("Category DEsc: "+ categoryDto.getDescription());
    // return categoryService.addCategory(userPrincipal, categoryDto);
    return null;
  }

  @PostMapping("/login")
  // public LoginResponse login(@RequestParam("categoryname") String categoryname, @RequestParam("file") MultipartFile file) {
  public ResponseEntity<HttpStatus> addNewCategory(
    @AuthenticationPrincipal UserPrincipal userPrincipal,
    @ModelAttribute CategoryDto categoryDto
  ) throws IOException {
    System.out.println("My Email: "+ categoryDto.getCategoryname());
    System.out.println("My Email: "+ categoryDto.getDescription());
    System.out.println("My Email: "+ categoryDto.getImageurl());
    return categoryService.addCategory(userPrincipal, categoryDto);
  }

  @PutMapping("/update/{catId}")
  public ResponseEntity<HttpStatus> updateCategory(
    @PathVariable("catId") Long catId,
    @ModelAttribute CategoryDto categoryDto
  ) {
    try {
      return categoryService.updateCategory(catId, categoryDto);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/delete/{catId}")
  public ResponseEntity<HttpStatus> deleteCategoryById(
    @PathVariable("catId") Long catId
  ) {
    try {
      return categoryService.deleteCategoryById(catId);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
}
