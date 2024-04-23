package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.RoleConstant;
import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc ;
    @MockBean
    ProductService productService ;
    private ProductRequest productRequest ;
    private Response response ;

    @BeforeEach
    void initData(){
        productRequest=  ProductRequest.builder()
                .productName("San pham test")
                .productPrice(2000)
                .brandId(1)
                .productQuantity(200)
                .productAvailable(true)
                .productPriceReduction(0)
                .build();
        response = Response.builder()
                .code(200)
                .build() ;
    }


    @Test
    void addProduct_validateRequest_forbidden () throws Exception {
        // GIVEN
        log.info("ADD product test ");
        ObjectMapper objectMapper = new ObjectMapper() ;
        String content = objectMapper.writeValueAsString(productRequest);
        Mockito.when(productService.addProduct(ArgumentMatchers.any())).thenReturn(response) ;

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/product/1012")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().is(403)) ;
    }

    @Test
    @WithMockUser(username = "user" , roles = {"ADMIN"})
    void addProduct_validateRequest_Success() throws Exception {
        log.info("ADD product test ");
        ObjectMapper objectMapper = new ObjectMapper() ;
        String content = objectMapper.writeValueAsString(productRequest);
        Mockito.when(productService.addProduct(ArgumentMatchers.any())).thenReturn(response) ;

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/product/1012")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)
                )
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("result.productName").value(productRequest.getProductName()));
    }

    @Test
    @WithMockUser(username = "admin" , roles = "ADMIN")
    void delProduct() throws Exception {
        // GIVEN
        response = Response.builder()
                .code(200)
                .build() ;

        // WHEN
        Mockito.when(productService.delProduct(ArgumentMatchers.any())).thenReturn(response) ;
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/1012/1,2,3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
