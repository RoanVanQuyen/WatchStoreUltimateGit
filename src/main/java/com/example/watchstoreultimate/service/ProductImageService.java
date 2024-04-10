package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImageService {
    Response addProductImage(int productId , MultipartFile file) throws IOException;
    Response updProductImage(int productImageId , int productId , MultipartFile file) throws  IOException;
    Response findProductImageById(int productImageId) ;
    Response findProductImageByProductId(int productId) ;
}
