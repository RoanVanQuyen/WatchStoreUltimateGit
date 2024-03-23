package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CategoryProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Category;
import com.example.watchstoreultimate.entity.CategoryProduct;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.repository.CategoryProductRepository;
import com.example.watchstoreultimate.repository.CategoryRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.CategoryProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    CategoryRepository categoryRepository ;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryProductRepository categoryProductRepository ;
    @Override
    public Response addCategoryProduct(CategoryProductRequest request) {
        List<Integer> categoryIds = request.getCategoryIds() ;
        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        List<CategoryProduct> categoryProducts = new ArrayList<>() ;
        for(Integer x : categoryIds){
            Optional<Category> categoryOptional = categoryRepository.findById(x) ;
            if(categoryOptional.isPresent()){
                CategoryProduct categoryProduct = CategoryProduct.builder()
                        .category(categoryOptional.get())
                        .product(product)
                        .build();
                categoryProductRepository.save(categoryProduct) ;
                categoryProducts.add(categoryProduct) ;
            }
        }
        return Response.builder()
                .code(200)
                .result(categoryProducts)
                .message("Add category for product success")
                .build();
    }

    @Override
    public Response delCategoryProduct(CategoryProduct categoryProduct) {
        return Response.builder()
                .code(200)
                .result(categoryProduct)
                .message("Delete success")
                .build();
    }
}
