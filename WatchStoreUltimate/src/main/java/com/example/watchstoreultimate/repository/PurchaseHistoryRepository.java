package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory , Integer> {

    @Query("FROM PurchaseHistory ph " +
            "WHERE ph.customer.customerId = :customerId ")
    List<PurchaseHistory> getHistoryByCustomer(int customerId) ;

    @Query("FROM PurchaseHistory ph " +
            "WHERE ph.product.productId=:productId")
    List<PurchaseHistory> getHistoryByProduct(int productId) ;
}
