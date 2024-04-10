package com.example.watchstoreultimate.controller;

import com.cloudinary.Cloudinary;
import com.example.watchstoreultimate.config.CloudinaryConfig;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.ProductImage;
import com.example.watchstoreultimate.service.ProductImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UrlConstant.ProductImageURL.PRE_FIX)
@Tag( name = "PRODUCT IMAGE API" ,  description = "REST API PRODUCT IMAGE FOR FRONT-END DEVELOPER")
public class ProductImageController {
    @Autowired
    private ProductImageService  productImageService ;
    @Autowired
    Cloudinary cloudinary ;
    @RequestMapping(value = UrlConstant.ProductImageURL.M_ADD_PRODUCT_IMAGE,method = RequestMethod.POST)
    public ResponseEntity<?> addProductImage(@PathVariable int productId , @RequestParam MultipartFile file) throws IOException {
        Response response = productImageService.addProductImage(productId , file) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(value = UrlConstant.ProductImageURL.M_UPD_PRODUCT_IMAGE, method = RequestMethod.PUT)
    public ResponseEntity<?> updProductImage(@PathVariable int productImageId ,
                                             @PathVariable int productId ,
                                             @RequestParam MultipartFile file) throws IOException {
        Response response = productImageService.updProductImage(productImageId , productId , file) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value= UrlConstant.ProductImageURL.FIND_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int request){
        Response response = productImageService.findProductImageById(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }



    @RequestMapping(value= UrlConstant.ProductImageURL.FIND_BY_PRODUCT, method = RequestMethod.GET)
    public ResponseEntity<?> findByProductId(@PathVariable int request){
        Response response = productImageService.findProductImageByProductId(request) ;
        response = Response.builder()
                .code(200)
                .result(response)
                .message("Find image by product success")
                .build() ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
