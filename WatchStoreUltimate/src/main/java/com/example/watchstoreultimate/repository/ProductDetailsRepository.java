package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.ProductDetails;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
@Hidden
public interface ProductDetailsRepository extends JpaRepository<ProductDetails , Integer> {
    @Query("FROM ProductDetails pd " +
            "WHERE pd.product.productId = :productId")
    Optional<ProductDetails> findByProductId(int productId) ;
}
