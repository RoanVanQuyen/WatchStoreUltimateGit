package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.ProductDetailsRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.entity.ProductDetails;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.ProductDetailsMapper;
import com.example.watchstoreultimate.repository.ProductDetailsRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    ProductDetailsMapper productDetailsMapper ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Override
    public Response addProductDetails(int productId , ProductDetailsRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        ProductDetails productDetails = productDetailsMapper.toProductDetails(request) ;
        productDetails.setProduct(product);
        return Response.builder()
                .code(200)
                .result(productDetailsRepository.save(productDetails))
                .message("Add product details success")
                .build();
    }

    @Override
    public Response updProductDetails(ProductDetails productDetails) {
        productDetailsRepository.findById(productDetails.getProductDetailsId()).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        return Response.builder()
                .code(200)
                .result(productDetailsRepository.save(productDetails))
                .message("Update product details success")
                .build();
    }

    @Override
    public Response findProductDetails(int productDetailsId) {
        ProductDetails productDetails = productDetailsRepository.findById(productDetailsId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        return Response.builder()
                .code(200)
                .result(productDetails)
                .message("Find product details success")
                .build();
    }

    @Override
    public Response findProductDetailsByProductId(int productId) {
        ProductDetails productDetails = productDetailsRepository.findByProductId(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        return Response.builder()
                .code(200)
                .result(productDetails)
                .message("Find product details success")
                .build();
    }
}
