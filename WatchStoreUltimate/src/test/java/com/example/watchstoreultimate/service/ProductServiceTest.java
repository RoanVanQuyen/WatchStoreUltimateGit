package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.repository.BrandRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
    @Autowired
    ProductService productService ;

    @MockBean
    ProductRepository productRepository;
    @MockBean
    BrandRepository brandRepository ;

    private ProductRequest request ;
    private Product product ;
    private Response response ;
    private Brand brand ;

    @BeforeEach
    void init(){
        request = ProductRequest.builder()
                .productName("Name'test")
                .productPrice(100)
                .productQuantity(200)
                .brandId(1)
                .build();

        brand = Brand.builder()
                .brandId(1)
                .brandAvailable(true)
                .brandName("brand name test")
                .brandDetails("details test")
                .build() ;
        product = Product.builder()
                .productName("Name'test")
                .productPrice(100)
                .productQuantity(200)
                .brand(brand)
                .productId(1)
                .build();
        response = Response.builder()
                .code(200)
                .build() ;
    }
    @Test
    void addProduct_success(){
        // GIVEN
        Mockito.when(productRepository.existsByProductName(ArgumentMatchers.anyString())).thenReturn(false) ;
        Mockito.when(brandRepository.findBrandByBrandIdAndBrandAvailable(ArgumentMatchers.anyInt() , ArgumentMatchers.anyBoolean())).thenReturn(Optional.ofNullable(brand)) ;

        // WHEN
        Response response =  productService.addProduct(request) ;

        // then
        Assertions.assertThat(response.getCode() == 200) ;
    }

    @Test
    void addProduct_productNameExisted(){
        // GIVEN
        Mockito.when(productRepository.existsByProductName(ArgumentMatchers.anyString())).thenReturn(true) ;
        Mockito.when(brandRepository.findBrandByBrandIdAndBrandAvailable(ArgumentMatchers.anyInt() , ArgumentMatchers.anyBoolean())).thenReturn(Optional.ofNullable(brand)) ;

        // WHEN
        Exception e = org.junit.jupiter.api.Assertions.assertThrows(AppException.class
                , () -> productService.addProduct(request)) ;

        // then
        Assertions.assertThat(response.getCode() == 200) ;
    }

}
