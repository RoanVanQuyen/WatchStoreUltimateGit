package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.CategoryProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.CategoryProduct;

public interface CategoryProductService {
    Response addCategoryProduct(CategoryProductRequest request) ;
    Response delCategoryProduct(CategoryProduct categoryProduct) ;
}
