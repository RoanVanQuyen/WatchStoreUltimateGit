package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.ProductDetailsRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.ProductDetails;

public interface ProductDetailsService {
    public Response addProductDetails(int productId , ProductDetailsRequest request) ;
    public Response updProductDetails(ProductDetails productDetails) ;
    public Response findProductDetails(int productDetailsId) ;
    public Response findProductDetailsByProductId(int productId) ;
}
