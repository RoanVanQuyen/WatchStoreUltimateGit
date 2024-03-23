package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
    boolean existsCategoryByCategoryName(String name ) ;
    Page<Category> findAllByCategoryAvailable( Pageable pageable ,boolean Available) ;
    Optional<Category> findByCategoryIdAndCategoryAvailable(Integer request ,boolean Available ) ;
}
