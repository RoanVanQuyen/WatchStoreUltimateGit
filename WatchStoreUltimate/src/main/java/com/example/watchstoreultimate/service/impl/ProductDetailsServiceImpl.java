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
import com.example.watchstoreultimate.service.RedisService;
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
    @Autowired
    RedisService redisService ;
    final static String KEY = "product-details" ;
    @Override
    public Response addProductDetails(int productId , ProductDetailsRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        ProductDetails productDetails = productDetailsMapper.toProductDetails(request) ;
        productDetails.setProduct(product);
        productDetailsRepository.save(productDetails) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(productDetails)
                .message("Add product details success")
                .build();
    }

    @Override
    public Response updProductDetails(ProductDetails productDetails) {
        productDetailsRepository.findById(productDetails.getProductDetailsId()).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        productDetailsRepository.save(productDetails) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(productDetails)
                .message("Update product details success")
                .build();
    }

    @Override
    public Response findProductDetails(int productDetailsId) {
        String field = "productDetailsId: "   + productDetailsId;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            ProductDetails productDetails = productDetailsRepository.findById(productDetailsId).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response = Response.builder()
                    .code(200)
                    .result(productDetails)
                    .message("Find product details success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response findProductDetailsByProductId(int productId) {
        String field = "productId: " + productId ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            ProductDetails productDetails = productDetailsRepository.findByProductId(productId).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response=  Response.builder()
                    .code(200)
                    .result(productDetails)
                    .message("Find product details success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }
}
