package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct( @RequestBody @Valid ProductRequest request){
        Response response = productService.addProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updProduct( @RequestBody @Valid Product request ){
        Response response = productService.updProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ProductURL.DEL_PRODUCT , method = RequestMethod.DELETE)
    public ResponseEntity<?> delProducts(@PathVariable List<Integer> productIds){
        Response response = productService.delProduct(productIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ProductURL.RE_DEL_PRODUCT , method = RequestMethod.PUT)
    public ResponseEntity<?> reDelProducts(@PathVariable List<Integer> productIds) {
        Response response = productService.reDelProduct(productIds);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @RequestMapping(value = UrlConstant.ProductURL.FIND_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> findProductById(@PathVariable int productId){
        Response response = productService.findProductById(productId);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }

    @RequestMapping(value = UrlConstant.ProductURL.FIND_BY_NAME_CONTAIN, method = RequestMethod.GET)
    public ResponseEntity<?> findProductByName(@PathVariable String productName ,
                                               @RequestParam(required = false, defaultValue = "1") int pageIndex ,
                                               @RequestParam(required = false, defaultValue = "9") int pageSize ,
                                               @RequestParam(required = false, defaultValue = "productName") String sortBy){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageSize , Sort.by(sortBy)) ;
        Response response = productService.findProductByName(productName , pageable);
        return ResponseEntity.status(response.getCode())
                .body(response);
    }
    @RequestMapping(value = UrlConstant.ProductURL.FILTER_PRODUCT,method = RequestMethod.GET)
    public ResponseEntity<?> filterProduct(@PathVariable List<Integer> productCategoryIds ,
                                           @PathVariable List<Integer> productBrandIds ,
                                           @RequestParam(defaultValue = "0" ,required = false) int minPrice ,
                                           @RequestParam(defaultValue = "999999999" ,required = false) int maxPrice ,
                                           @RequestParam(defaultValue = "1", required = false) int pageIndex ,
                                           @RequestParam(defaultValue = "9" , required = false) int pageSize ,
                                           @RequestParam(defaultValue = "productName",required = false) String sortBy){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageSize , Sort.by(sortBy)) ;
        Response response = productService.filterProduct(productCategoryIds , productBrandIds , minPrice , maxPrice , pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response);
    }
}
