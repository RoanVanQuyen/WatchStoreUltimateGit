package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.entity.ProductImage;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.repository.ProductImageRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.ProductImageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository ;
    @Autowired
    ProductRepository productRepository ;
    @Override
    public Response addProductImage(int productId , MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        ProductImage productImage = ProductImage.builder()
                .imageName(file.getOriginalFilename())
                .imageData(file.getBytes())
                .imageType(file.getContentType())
                .product(product)
                .build();
        if(productImageRepository.existsByImageName(file.getOriginalFilename())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        return Response.builder()
                .code(200)
                .result(productImageRepository.save(productImage))
                .message("Add product' image success")
                .build() ;
    }

    @Override
    public Response updProductImage(int productImageId, int productId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        ProductImage productImage = ProductImage.builder()
                .productImageId(productImageId)
                .imageName(file.getOriginalFilename())
                .imageData(file.getBytes())
                .imageType(file.getContentType())
                .product(product)
                .build();
        if(productImageRepository.existsByImageName(file.getOriginalFilename())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        return Response.builder()
                .code(200)
                .result(productImageRepository.save(productImage))
                .message("Add product' image success")
                .build() ;
    }


    @Override
    public Response findProductImageById(int productImageId) {
        ProductImage productImage = productImageRepository.findById(productImageId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(productImage)
                .message("Find product' image by id success")
                .build() ;
    }

    @Override
    public Response findProductImageByProductId(int productId) {
        List<ProductImage> productImages = new ArrayList<>() ;
        productImages = productImageRepository.findAllByProduct(productId) ;
        return Response.builder()
                .code(200)
                .result(productImages)
                .message("Find product' image by product success")
                .build() ;
    }

    @Override
    public Response findProductImageByName(String name) {
        ProductImage productImage = productImageRepository.findByImageName(name).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(productImage)
                .message("Find product' image by name success")
                .build() ;
    }
}
