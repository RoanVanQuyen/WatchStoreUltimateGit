package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails , Integer> {
    @Query("FROM ProductDetails pd " +
            "WHERE pd.product.productId = :productId")
    Optional<ProductDetails> findByProductId(int productId) ;
}
