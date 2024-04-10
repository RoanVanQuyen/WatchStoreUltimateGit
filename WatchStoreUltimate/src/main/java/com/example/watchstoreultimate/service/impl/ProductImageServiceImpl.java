package com.example.watchstoreultimate.service.impl;

import com.cloudinary.Cloudinary;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.entity.ProductImage;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.repository.ProductImageRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.ProductImageService;
import com.example.watchstoreultimate.service.RedisService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    Cloudinary cloudinary ;
    @Autowired
    RedisService redisService  ;
    final static String KEY = "product-image" ;
    @Override
    public Response addProductImage(int productId , MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Map data = cloudinary.uploader().upload(file.getBytes() , Map.of()) ; // không up được file quá 10 MB
        ProductImage productImage = ProductImage.builder()
                .productImageUrl((String) data.get("url"))
                .product(product)
                .build();
        productImageRepository.save(productImage) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(productImage)
                .message("Add product' image success")
                .build() ;
    }

    @Override
    public Response updProductImage(int productImageId, int productId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        ProductImage productImage = productImageRepository.findById(productImageId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Map data = cloudinary.uploader().upload(file.getBytes() , Map.of());
        productImage.setProductImageUrl((String)data.get("url"));
        productImageRepository.save(productImage) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(productImage)
                .message("Update product' image success")
                .build() ;
    }


    @Override
    public Response findProductImageById(int productImageId) {
        String field = "productImageId: "  + productImageId ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            ProductImage productImage = productImageRepository.findById(productImageId).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response = Response.builder()
                    .code(200)
                    .result(productImage)
                    .message("Find product' image by id success")
                    .build() ;
            redisService.hashSet(KEY , field , response );
        }
        return response  ;
    }

    @Override
    public Response findProductImageByProductId(int productId) {
        String field =  "productId: " + productId ;
        Response response = (Response) redisService.hashGet(KEY , field );
        if(response == null) {
            List<ProductImage> productImages = new ArrayList<>();
            productImages = productImageRepository.findAllByProduct(productId);
            response =  Response.builder()
                    .code(200)
                    .result(productImages)
                    .message("Find product' image by product success")
                    .build();
            redisService.hashSet(KEY , field , response );
        }
        return response ;
    }

}
