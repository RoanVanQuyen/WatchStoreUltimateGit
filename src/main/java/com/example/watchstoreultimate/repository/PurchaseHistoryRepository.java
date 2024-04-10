package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.PurchaseHistory;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
@Hidden
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory , Integer> {

    @Query("FROM PurchaseHistory ph " +
            "WHERE ph.customer.customerId = :customerId ")
    List<PurchaseHistory> getHistoryByCustomer(int customerId) ;

    @Query("FROM PurchaseHistory ph " +
            "WHERE ph.product.productId=:productId")
    List<PurchaseHistory> getHistoryByProduct(int productId) ;

    @Query("FROM  PurchaseHistory ph " +
            "WHERE ph.purchaseHistoryDay > :date1 " +
            "and ph.purchaseHistoryDay < :date2")
    List<PurchaseHistory> findAllByDate(LocalDate date1 , LocalDate date2) ;
}
