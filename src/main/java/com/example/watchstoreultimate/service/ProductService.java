package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    static final boolean PRODUCT_AVAILABLE_NOT = false ;
    static final boolean PRODUCT_AVAILABLE = true ;
    Response addProduct(ProductRequest request) ;
    Response updProduct(int productId , ProductRequest request) ;
    Response delProduct(List<Integer> request) ;
    Response reDelProduct(List<Integer> request) ;
    Response getFeatureProduct() ;
    Response getNewProduct() ;
    Response getSaleProduct() ;
    Response findProductByName(String request, Pageable pageable) ;
    Response filterProduct(List<Integer> productCategoryIds , List<Integer> productBrandIds
                    , int minPrice , int maxPrice , Pageable pageable);
    Response findProductById(int request) ;

}
