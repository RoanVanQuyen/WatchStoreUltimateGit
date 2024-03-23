package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    boolean existsByImageName(String name) ;
    Optional<ProductImage> findByImageName(String name) ;
    @Query("FROM ProductImage pi " +
            "WHERE pi.product.productId = :productId")
    List<ProductImage> findAllByProduct(int productId) ;

}
