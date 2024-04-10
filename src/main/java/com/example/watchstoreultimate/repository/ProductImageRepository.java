package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.ProductImage;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
@Hidden
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query("FROM ProductImage pi " +
            "WHERE pi.product.productId = :productId")
    List<ProductImage> findAllByProduct(int productId) ;

}
