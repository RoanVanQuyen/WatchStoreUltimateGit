package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.response.Response;

public interface PurchaseHistoryService {
    Response getHistoryByCustomer(int customerId) ;
    Response getHistoryByProduct(int productId) ;
}
