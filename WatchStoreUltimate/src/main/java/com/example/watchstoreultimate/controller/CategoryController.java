package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CategoryProductRequest;
import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Category;
import com.example.watchstoreultimate.entity.CategoryProduct;
import com.example.watchstoreultimate.service.CategoryProductService;
import com.example.watchstoreultimate.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.CategoryURL.PRE_FIX)
public class CategoryController {
    @Autowired
    private CategoryService categoryService ;
    @Autowired
    private CategoryProductService categoryProductService ;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryRequest request){
        Response response = categoryService.addCategory(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updCategory(@RequestBody @Valid Category request){
        Response response = categoryService.updCategory(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CategoryURL.DEL_CATEGORY, method = RequestMethod.DELETE)
    public ResponseEntity<?> delCategory(@PathVariable List<Integer> categoryIds){
        Response response = categoryService.delCategory(categoryIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CategoryURL.RE_DEL_CATEGORY, method = RequestMethod.PUT)
    public ResponseEntity<?> reDelCategory(@PathVariable List<Integer> categoryIds){
        Response response = categoryService.reDelCategory(categoryIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CategoryURL.FIND_BY_ID,method = RequestMethod.GET)
    public ResponseEntity<?> findCategoryById(@PathVariable Integer categoryId){
        Response response = categoryService.findCategoryById(categoryId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(value = UrlConstant.CategoryURL.FIND_ALL , method = RequestMethod.GET)
    public ResponseEntity<?> findCategory(@RequestParam(defaultValue = "1", required = false) Integer pageIndex  ,
                                          @RequestParam(defaultValue = "9", required = false) Integer pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1, pageElement) ;
        Response response = categoryService.findAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @RequestMapping(value= UrlConstant.CategoryURL.CATEGORY_PRODUCT, method = RequestMethod.POST)
    public ResponseEntity<?> addCategoryForProduct(@RequestBody CategoryProductRequest request){
        Response response = categoryProductService.addCategoryProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CategoryURL.CATEGORY_PRODUCT, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategoryForProduct(@RequestBody CategoryProduct categoryProduct){
        Response response = categoryProductService.delCategoryProduct(categoryProduct) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

}
