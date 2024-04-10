package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.PurchaseHistory;
import com.example.watchstoreultimate.repository.PurchaseHistoryRepository;
import com.example.watchstoreultimate.service.CustomerService;
import com.example.watchstoreultimate.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Response getTotalSales() {
        Long ans = 0L ;
        List<PurchaseHistory> purchaseHistories = new ArrayList<>() ;
        purchaseHistories = purchaseHistoryRepository.findAll() ;
        for(PurchaseHistory x : purchaseHistories){
            ans = ans + x.getQuantity() * x.getProduct().getProductPrice() ;
        }
        return Response.builder()
                .message("Get total sale success")
                .code(200)
                .result(ans)
                .build() ;
    }

    @Override
    public Response getAll(Pageable pageable) {
        List<PurchaseHistory> purchaseHistories = new ArrayList<>() ;
        purchaseHistories = purchaseHistoryRepository.findAll(pageable).getContent();
        return Response.builder()
                .code(200)
                .message("Get all")
                .result(purchaseHistories)
                .build();
    }
}
