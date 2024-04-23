package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Cart;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.idclass.CustomerProduct;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
@Hidden
public interface CartRepository extends JpaRepository<Cart , CustomerProduct> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsByCustomerAndProduct(Customer customer , Product product) ;
    @Lock(LockModeType.WRITE)
    Optional<Cart> findByCustomerAndProduct(Customer customer , Product product) ;

    @Query("FROM  Cart c " +
            "WHERE c.product.productId = :productId AND " +
            "c.customer.customerId =:customerId ")
    Optional<Cart> getCartByProductIdAndCustomerId(int productId , int customerId) ;
    @Query(value = "FROM Cart c " +
            "WHERE c.product.productId = :productId")
    List<Cart> getCartByProductId(int productId) ;
    @Query(value = "FROM Cart c " +
            "WHERE c.customer.customerId = :customerId")
    List<Cart> getCartByCustomerId(int customerId) ;
}
