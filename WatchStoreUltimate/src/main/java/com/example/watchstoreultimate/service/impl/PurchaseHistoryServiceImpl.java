package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.PurchaseHistory;
import com.example.watchstoreultimate.repository.PurchaseHistoryRepository;
import com.example.watchstoreultimate.service.CustomerService;
import com.example.watchstoreultimate.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository ;
    @Override
    public Response getHistoryByCustomer(int customerId) {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.getHistoryByCustomer(customerId) ;
        return Response.builder()
                .code(200)
                .result(purchaseHistories)
                .message("Get by customer success")
                .build();
    }

    @Override
    public Response getHistoryByProduct(int productId) {
        List<PurchaseHistory> purchaseHistories = purchaseHistoryRepository.getHistoryByProduct(productId) ;
        return Response.builder()
                .code(200)
                .result(purchaseHistories)
                .message("Get by product success")
                .build();
    }
}
