package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.ProductMapper;
import com.example.watchstoreultimate.repository.BrandRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    BrandRepository brandRepository ;
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
        return Response.builder()
                .code(200)
                .result(productRepository.save(product))
                .message("Add product success")
                .build();
    }

    @Override
    public Response updProduct(Product request) {
        Product productDb = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(productRepository.existsByProductName(request.getProductName()) && !productDb.getProductName().equals(request.getProductName())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        return Response.builder()
                .code(200)
                .result(productRepository.save(request))
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
        return Response.builder()
                .code(200)
                .result(products)
                .message("Restore delete products success")
                .build();
    }

    @Override
    public Response findProductByName(String request, Pageable pageable) {
        List<Product> products = new ArrayList<>() ;
        products = productRepository.findByProductNameContainsIgnoreCaseAndProductAvailable(request , pageable ,  PRODUCT_AVAILABLE) ;
        return Response.builder()
                .code(200)
                .result(products)
                .message("Find product by name success")
                .build()  ;
    }

    @Override
    public Response filterProduct(List<Integer> productCategoryIds, List<Integer> productBrandIds, int minPrice, int maxPrice, Pageable pageable) {
        List<Product> products = new ArrayList<>() ;
        products = productRepository.filterProduct( productCategoryIds ,productBrandIds , minPrice , maxPrice , pageable, PRODUCT_AVAILABLE) ;
        return Response.builder()
                .code(200)
                .result(products)
                .message("Filter product success")
                .build();
    }

    @Override
    public Response findProductById(int request) {
        Optional<Product> productOptional= productRepository.findByProductIdAndProductAvailable(request, PRODUCT_AVAILABLE) ;
        Product product = productOptional.orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(product)
                .message("Find product by product id success")
                .build();
    }
}
