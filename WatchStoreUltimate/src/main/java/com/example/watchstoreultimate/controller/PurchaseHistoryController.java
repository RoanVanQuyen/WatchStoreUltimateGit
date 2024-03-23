package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase-history")
public class PurchaseHistoryController {
    @Autowired
    PurchaseHistoryService purchaseHistoryService ;

    @RequestMapping(value = "customer/{customerId}")
    public ResponseEntity<?> getByCustomer(@PathVariable int customerId){
        Response response = purchaseHistoryService.getHistoryByCustomer(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = "product/{productId}")
    public ResponseEntity<?> getByProduct(@PathVariable int productId){
        Response response = purchaseHistoryService.getHistoryByProduct(productId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
