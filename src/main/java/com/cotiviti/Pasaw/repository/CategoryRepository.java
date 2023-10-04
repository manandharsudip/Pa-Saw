package com.cotiviti.Pasaw.repository;

import com.cotiviti.Pasaw.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
  extends JpaRepository<CategoryEntity, Long> {
  Optional<CategoryEntity> findById(Long id);
}
