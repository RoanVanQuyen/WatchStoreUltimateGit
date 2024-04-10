package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CategoryProductRequest;
import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.CategoryProduct;
import com.example.watchstoreultimate.service.CategoryProductService;
import com.example.watchstoreultimate.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.CategoryURL.PRE_FIX)
@Tag( name = "CATEGORY API" ,  description = "REST API CATEGORY FOR FRONT-END DEVELOPER")
public class CategoryController {
    @Autowired
    private CategoryService categoryService ;
    @Autowired
    private CategoryProductService categoryProductService ;

    @Operation(summary = "ADD CATEGORY")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = ErrorResponseCode.NAME_EXISTED  + "", description = ErrorResponseMessage.NAME_EXISTED)
            }
    )
    @RequestMapping(value = UrlConstant.ROLE_MANAGER ,method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryRequest request){
        Response response = categoryService.addCategory(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "UPDATE INFO CATEGORY")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = ErrorResponseCode.NAME_EXISTED  + "", description = ErrorResponseMessage.NAME_EXISTED)
                    , @ApiResponse(responseCode =  ErrorResponseCode.ERR_ID_NOT_FOUND +"" , description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
            }
    )
    @RequestMapping(value = UrlConstant.CategoryURL.U_UPD_CATEGORY,method = RequestMethod.PUT)
    public ResponseEntity<?> updCategory(@PathVariable int categoryId,
                                         @RequestBody @Valid CategoryRequest request){
        Response response = categoryService.updCategory(categoryId , request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "DELETE INFO CATEGORY")
    @RequestMapping(value = UrlConstant.CategoryURL.M_DEL_CATEGORY, method = RequestMethod.DELETE)
    public ResponseEntity<?> delCategory(@PathVariable List<Integer> categoryIds){
        Response response = categoryService.delCategory(categoryIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "RETRIEVE INFO CATEGORY")
    @RequestMapping(value = UrlConstant.CategoryURL.M_RE_DEL_CATEGORY, method = RequestMethod.PUT)
    public ResponseEntity<?> reDelCategory(@PathVariable List<Integer> categoryIds){
        Response response = categoryService.reDelCategory(categoryIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "GET CATEGORY BY ID")
    @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    @RequestMapping(value = UrlConstant.CategoryURL.FIND_BY_ID,method = RequestMethod.GET)
    public ResponseEntity<?> findCategoryById(@PathVariable Integer categoryId){
        Response response = categoryService.findCategoryById(categoryId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary =  "FIND ALL")
    @RequestMapping(value = UrlConstant.CategoryURL.FIND_ALL , method = RequestMethod.GET)
    public ResponseEntity<?> findCategory(@RequestParam(defaultValue = "1", required = false) Integer pageIndex  ,
                                          @RequestParam(defaultValue = "9", required = false) Integer pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1, pageElement) ;
        Response response = categoryService.findAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }



    @Operation(summary = "INSERT CATEGORY-PRODUCT")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND  + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
            }
    )
    @RequestMapping(value= UrlConstant.CategoryURL.M, method = RequestMethod.POST)
    public ResponseEntity<?> addCategoryForProduct(@RequestBody CategoryProductRequest request){
        Response response = categoryProductService.addCategoryProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "DELETE CATEGORY-PRODUCT")
    @RequestMapping(value = UrlConstant.CategoryURL.M, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategoryForProduct(@RequestBody CategoryProduct categoryProduct){
        Response response = categoryProductService.delCategoryProduct(categoryProduct) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

}
