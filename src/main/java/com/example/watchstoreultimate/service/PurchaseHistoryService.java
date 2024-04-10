package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface PurchaseHistoryService {
    Response getHistoryByCustomer(int customerId) ;
    Response getHistoryByProduct(int productId) ;
    Response getTotalSales() ;
    Response getAll(Pageable pageable) ;
}
