package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.ProductDetailsRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.ProductDetails;
import com.example.watchstoreultimate.service.ProductDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.ProductDetailsURL.PRE_FIX)
@Tag( name = "PRODUCT DETAILS API" ,  description = "REST API PRODUCT DETAILS FOR FRONT-END DEVELOPER")
public class ProductDetailsController {
    @Autowired
    ProductDetailsService productDetailsService ;

    @RequestMapping(value = UrlConstant.ROLE_MANAGER , method = RequestMethod.POST)
    public ResponseEntity<?> addProductDetails(@PathVariable int productId , @RequestBody ProductDetailsRequest request){
        Response response = productDetailsService.addProductDetails(productId ,request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ROLE_MANAGER ,  method = RequestMethod.PUT)
    public ResponseEntity<?> updProductDetails(@RequestBody ProductDetails productDetails){
        Response response = productDetailsService.updProductDetails(productDetails) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ProductDetailsURL.FIND_BY_ID , method = RequestMethod.GET)
    public ResponseEntity<?> findProductDetails(@PathVariable int productDetailsId){
        Response response = productDetailsService.findProductDetails(productDetailsId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ProductDetailsURL.FIND_BY_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> findProductDetailsByProductId(@PathVariable int productId){
        Response response = productDetailsService.findProductDetailsByProductId(productId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
