package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.ProductURL.PRE_FIX)
@Tag( name = "PRODUCT API",  description = "REST API PRODUCT FOR FRONT-END DEVELOPER")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "ADD PRODUCT ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
            , @ApiResponse(responseCode =  ErrorResponseCode.NAME_EXISTED + "" , description = ErrorResponseMessage.NAME_EXISTED)
    })
    @RequestMapping(value = UrlConstant.ROLE_MANAGER , method = RequestMethod.POST)
    public ResponseEntity<?> addProduct( @RequestBody @Valid ProductRequest request){
        Response response = productService.addProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "UPDATE INFO PRODUCT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
            , @ApiResponse(responseCode =  ErrorResponseCode.NAME_EXISTED + "" , description = ErrorResponseMessage.NAME_EXISTED)
    })
    @RequestMapping(value = UrlConstant.ProductURL.M_UPD_PRODUCT,method = RequestMethod.PUT)
    public ResponseEntity<?> updProduct( @PathVariable int productId ,
                                         @RequestBody @Valid ProductRequest request ){
        Response response = productService.updProduct(productId , request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "DELETE INFO PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.M_DEL_PRODUCT, method = RequestMethod.DELETE)
    public ResponseEntity<?> delProducts(@PathVariable List<Integer> productIds){
        Response response = productService.delProduct(productIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "RESTORE DELETE INFO PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.M_RE_DEL_PRODUCT, method = RequestMethod.POST)
    public ResponseEntity<?> reDelProducts(@PathVariable List<Integer> productIds) {
        Response response = productService.reDelProduct(productIds);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @Operation(summary = "GET PRODUCT BY ID")
    @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    @RequestMapping(value = UrlConstant.ProductURL.FIND_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> findProductById(@PathVariable int productId){
        Response response = productService.findProductById(productId);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @Operation(summary = "GET PRODUCT BY PRODUCT NAME")
    @RequestMapping(value = UrlConstant.ProductURL.FIND_BY_NAME_CONTAIN, method = RequestMethod.GET)
    public ResponseEntity<?> findProductByName(@PathVariable String productName ,
                                               @RequestParam(required = false, defaultValue = "1") int pageIndex ,
                                               @RequestParam(required = false, defaultValue = "9") int pageElement,
                                               @RequestParam(required = false, defaultValue = "productName") String sortBy){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement, Sort.by(sortBy)) ;
        Response response = productService.findProductByName(productName , pageable);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @Operation(summary = "FILTER PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.FILTER_PRODUCT,method = RequestMethod.GET)
    public ResponseEntity<?> filterProduct(@PathVariable List<Integer> productCategoryIds ,
                                           @PathVariable List<Integer> productBrandIds ,
                                           @RequestParam(defaultValue = "0" ,required = false) int minPrice ,
                                           @RequestParam(defaultValue = "999999999" ,required = false) int maxPrice ,
                                           @RequestParam(defaultValue = "1", required = false) int pageIndex ,
                                           @RequestParam(defaultValue = "9" , required = false) int PageElement,
                                           @RequestParam(defaultValue = "productName",required = false) String sortBy){
        Pageable pageable = PageRequest.of(pageIndex -1 , PageElement, Sort.by(sortBy)) ;
        Response response = productService.filterProduct(productCategoryIds , productBrandIds , minPrice , maxPrice , pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @Operation(summary = "GET FEATURE PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.GET_FEATURE_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> getFeatureProduct(){
        Response response = productService.getFeatureProduct() ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }


    @Operation(summary = "GET NEW PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.GET_NEW_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> getNewProduct(){
        Response response = productService.getNewProduct() ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }
    @Operation(summary = "GET SALE 'PRODUCT")
    @RequestMapping(value = UrlConstant.ProductURL.GET_SALE_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> getSaleProduct(){
        Response response = productService.getSaleProduct() ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }
}
