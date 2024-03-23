package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.ProductImage;
import com.example.watchstoreultimate.service.ProductImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.ProductImageURL.PRE_FIX)
public class ProductImageController {
    @Autowired
    private ProductImageService  productImageService ;


    @RequestMapping(value = UrlConstant.ProductImageURL.ADD_PRODUCT_IMAGE ,method = RequestMethod.POST)
    public ResponseEntity<?> addProductImage(@PathVariable int productId , @RequestParam MultipartFile file) throws IOException {
        Response response = productImageService.addProductImage(productId , file) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ProductImageURL.UPD_PRODUCT_IMAGE, method = RequestMethod.PUT)
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
        ProductImage productImage = (ProductImage) response.getResult();
        return ResponseEntity.status(response.getCode())
                .contentType(MediaType.valueOf(productImage.getImageType()))
                .body(productImage.getImageData());
    }

    @RequestMapping(value= UrlConstant.ProductImageURL.FIND_BY_NAME, method = RequestMethod.GET)
    public ResponseEntity<?> findByName(@PathVariable String request){
        Response response = productImageService.findProductImageByName(request) ;
        ProductImage productImage = (ProductImage) response.getResult();
        return ResponseEntity.status(response.getCode())
                .contentType(MediaType.valueOf(productImage.getImageType()))
                .body(productImage.getImageData());
    }

    @RequestMapping(value= UrlConstant.ProductImageURL.FIND_BY_PRODUCT, method = RequestMethod.GET)
    public ResponseEntity<?> findByProductId(@PathVariable int request){
        Response response = productImageService.findProductImageByProductId(request) ;
        List<ProductImage> productImages = (List<ProductImage>) response.getResult();
        List<String> urls = new ArrayList<>() ;
        for(ProductImage x : productImages){
            urls.add("localhost:8080/product/image/id/"+ x.getProductImageId())  ;
        }
        response = Response.builder()
                .code(200)
                .result(urls)
                .message("Find image by product success")
                .build() ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
