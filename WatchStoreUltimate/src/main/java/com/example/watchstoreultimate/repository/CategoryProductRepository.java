package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.idclass.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<com.example.watchstoreultimate.entity.CategoryProduct, CategoryProduct> {
}
