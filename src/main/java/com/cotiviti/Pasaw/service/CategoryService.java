package com.cotiviti.Pasaw.service;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.security.UserPrincipal;

public interface CategoryService {
    public ResponseEntity<HttpStatus> addCategory(UserPrincipal principal, CategoryEntity category);
    public ResponseEntity<List<CategoryEntity>> getAllCategory();
    public ResponseEntity<CategoryEntity> getCategoryById(Long catId);
    public ResponseEntity<HttpStatus> updateCategory(Long catId, CategoryEntity category);
    public ResponseEntity<HttpStatus> deleteCategory(Long catId);
}
