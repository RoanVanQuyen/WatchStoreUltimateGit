package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.idclass.CategoryProduct;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
@Hidden
public interface CategoryProductRepository extends JpaRepository<com.example.watchstoreultimate.entity.CategoryProduct, CategoryProduct> {
}
