package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Product;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductIdAndProductAvailable(int productId , boolean Available) ;
    List<Product> findByProductNameContainsIgnoreCaseAndProductAvailable(String name , Pageable pageable, boolean Available) ;
    boolean existsByProductName(String name) ;
    @Query(value = "SELECT  p " +
            "FROM Product p " +
            "LEFT JOIN CategoryProduct c on c.product.productId = p.productId " +
            "WHERE ( p.brand.brandId IN :productBrandIds " +
            "OR " +
            "c.category.categoryId IN :productCategoryIds) " +
            "AND " +
            "(p.productPrice >= :minPrice AND p.productPrice <= :maxPrice AND p.productAvailable = :available ) ")
    List<Product> filterProduct(List<Integer> productCategoryIds , List<Integer> productBrandIds
            , int minPrice , int maxPrice , Pageable pageable, boolean available) ;


}
