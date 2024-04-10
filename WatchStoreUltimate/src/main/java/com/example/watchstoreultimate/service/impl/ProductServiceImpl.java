package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.ProductMapper;
import com.example.watchstoreultimate.repository.BrandRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.ProductService;
import com.example.watchstoreultimate.service.RedisService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    BrandRepository brandRepository ;
    @Autowired
    RedisService redisService;
    final static String KEY = "product" ;

    private void deleteCache(){
        Set<String> set =  redisService.getByFieldPrefix(KEY) ;
        for(String x : set){
            redisService.delete(KEY , x);
        }
    }
    @Override
    public Response addProduct(ProductRequest request) {
        if(productRepository.existsByProductName(request.getProductName())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        Brand brand = brandRepository.findBrandByBrandIdAndBrandAvailable(request.getBrandId() , PRODUCT_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Product product = productMapper.toProduct(request) ;
        product.setBrand(brand);
        productRepository.save(product) ;
        deleteCache();
        return Response.builder()
                .code(200)
                .result(product)
                .message("Add product success")
                .build();
    }

    @Override
    public Response updProduct(int productId , ProductRequest request) {
        Product productDb = productRepository.findById(productId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(productRepository.existsByProductName(request.getProductName()) && !productDb.getProductName().equals(request.getProductName())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        productMapper.updProduct(productDb,request);
        productRepository.save(productDb) ;
        deleteCache();
        return Response.builder()
                .code(200)
                .result(productDb)
                .message("Update product success")
                .build();
    }

    @Override
    public Response delProduct(List<Integer> request) {
        List<Product> products = new ArrayList<>() ;
        for(Integer x : request){
            Optional<Product> productOptional = productRepository.findById(x) ;
            if(productOptional.isPresent()){
                productOptional.get().setProductAvailable(PRODUCT_AVAILABLE_NOT);
                productRepository.save(productOptional.get()) ;
                products.add(productOptional.get()) ;
            }
        }
        deleteCache();
        return Response.builder()
                .code(200)
                .result(products)
                .message("Delete products success")
                .build();
    }

    @Override
    public Response reDelProduct(List<Integer> request) {
        List<Product> products = new ArrayList<>() ;
        for(Integer x : request){
            Optional<Product> productOptional = productRepository.findById(x) ;
            if(productOptional.isPresent()){
                productOptional.get().setProductAvailable(PRODUCT_AVAILABLE);
                productRepository.save(productOptional.get()) ;
                products.add(productOptional.get()) ;
            }
        }
        deleteCache();
        return Response.builder()
                .code(200)
                .result(products)
                .message("Restore delete products success")
                .build();
    }

    @Override
    public Response getFeatureProduct() {
        String field = "getFeatureProduct" ;
        Response response = (Response) redisService.hashGet(KEY, field);
        if(response == null){
            List<Product> products = productRepository.getFeatureProduct() ;
            response = Response.builder()
                    .code(200)
                    .result(products)
                    .message("Get feature product success")
                    .build() ;
            redisService.hashSet(KEY , field , response);
        }
        return response;
    }

    @Override
    public Response getNewProduct() {
        String field = "getNewProduct" ;
        Response response = (Response) redisService.hashGet(KEY  , field);
        if(response == null){
            List<Product> products = productRepository.getNewProduct() ;
            response = Response.builder()
                    .code(200)
                    .result(products)
                    .message("Get new product success")
                    .build() ;
            redisService.hashSet(KEY , field , response);
        }
        return response;
    }

    @Override
    public Response getSaleProduct() {
        String field = "getSaleProduct" ;
        Response response = (Response) redisService.hashGet(KEY  , field);
        if(response == null){
            List<Product> products = productRepository.getSaleProduct() ;
            response = Response.builder()
                    .code(200)
                    .result(products)
                    .message("Get sale product success")
                    .build() ;
            redisService.hashSet(KEY , field , response);
        }
        return response;
    }

    @Override
    public Response findProductByName(String request, Pageable pageable) {
        String field = request + ":" + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null){
            Page<Product> productPage = productRepository.findByProductNameContainsIgnoreCaseAndProductAvailable(request , pageable ,  PRODUCT_AVAILABLE) ;
            PageCustom<Product> pageCustom = PageCustom.<Product>builder()
                    .pageIndex(pageable.getPageNumber() + 1)
                    .pageElement(pageable.getPageSize())
                    .pageSize(productPage.getTotalPages())
                    .content(productPage.getContent())
                    .sort(productPage.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .result(pageCustom)
                    .message("Find product by name success")
                    .build()  ;
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response filterProduct(List<Integer> productCategoryIds, List<Integer> productBrandIds, int minPrice, int maxPrice, Pageable pageable) {
        String field =productCategoryIds+":" +productBrandIds +":" +minPrice +":" +maxPrice +":" +pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Page<Product> productPage = productRepository.filterProduct(productCategoryIds, productBrandIds, minPrice, maxPrice, pageable, PRODUCT_AVAILABLE);
            PageCustom<Product> pageCustom = PageCustom.<Product>builder()
                    .pageIndex(pageable.getPageNumber() + 1)
                    .pageElement(pageable.getPageSize())
                    .pageSize(productPage.getTotalPages())
                    .content(productPage.getContent())
                    .sort(productPage.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .result(pageCustom)
                    .message("Filter product success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response findProductById(int request) {
        String field = request + "" ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Optional<Product> productOptional = productRepository.findByProductIdAndProductAvailable(request, PRODUCT_AVAILABLE);
            Product product = productOptional.orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response = Response.builder()
                    .code(200)
                    .result(product)
                    .message("Find product by product id success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }
}
