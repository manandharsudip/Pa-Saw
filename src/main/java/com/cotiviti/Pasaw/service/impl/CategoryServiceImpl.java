package com.cotiviti.Pasaw.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cotiviti.Pasaw.entity.CategoryEntity;
import com.cotiviti.Pasaw.repository.CategoryRepository;
import com.cotiviti.Pasaw.repository.UserRepository;
import com.cotiviti.Pasaw.security.UserPrincipal;
import com.cotiviti.Pasaw.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<HttpStatus> addCategory(UserPrincipal principal, CategoryEntity category) {
        
        userRepository.findByEmail(principal.getEmail()).map(user ->{
            category.setUserEntity(user);
            return categoryRepository.save(category);
        });

        // System.out.println("This is working!!!: Current Active User" + principal.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        List<CategoryEntity> cats = categoryRepository.findAll();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryEntity> getCategoryById(Long catId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public ResponseEntity<HttpStatus> updateCategory(Long catId, CategoryEntity category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCategory(Long catId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }
    
}
