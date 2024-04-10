package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.BlogRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = UrlConstant.BlogUrl.PRE_FIX)
@Tag(name = "BLOG API" , description = "")
public class BlogController {

    @Autowired
    BlogService blogService ;

    @Operation(summary = "ADD BLOG")
    @RequestMapping(value =  UrlConstant.ROLE_MANAGER ,  method = RequestMethod.POST)
    public ResponseEntity<?> addBlog(@RequestBody @Valid BlogRequest request){
        Response response = blogService.addBlog(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "UPDATE BLOG")
    @RequestMapping(value = UrlConstant.BlogUrl.UPD_BLOG,  method = RequestMethod.PUT)
    public ResponseEntity<?> updBlog(@PathVariable int blogId ,  @RequestBody @Valid BlogRequest request){
        Response response = blogService.updBlog(blogId , request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @Operation(summary = "DELETE BLOG")
    @RequestMapping(value = UrlConstant.BlogUrl.DEL_BLOG, method = RequestMethod.DELETE)
    public ResponseEntity<?> delBlogs(@PathVariable List<Integer> blogIds){
        Response response = blogService.delBlog(blogIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "GET BLOG BY ID")
    @RequestMapping(value = UrlConstant.BlogUrl.GET_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> getBlogById(@PathVariable int blogId){
        Response response = blogService.getById(blogId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "GET BLOG BY CUSTOMER ID")
    @RequestMapping(value = UrlConstant.BlogUrl.GET_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> getBlogByCustomer(@PathVariable int customerId,
                                               @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                               @RequestParam(defaultValue = "9" , required = false) int pageSize ,
                                               @RequestParam(defaultValue = "view" ,required = false) String sortBy,
                                               @RequestParam(defaultValue = "asc", required = false) String sortType){
        Sort sort = (sortType.equals("asc")) ? Sort.by(sortBy) : Sort.by(sortBy).descending() ;
        Pageable pageable = PageRequest.of(pageIndex -1  , pageSize , sort) ;
        Response response = blogService.getByCustomer(customerId, pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "GET BLOG" , description = "GET ALL")
    @RequestMapping(value = UrlConstant.BlogUrl.GET_ALL, method = RequestMethod.GET)
    public ResponseEntity<?> getBlog(@RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                    @RequestParam(defaultValue = "9" , required = false) int pageSize,
                                     @RequestParam(defaultValue = "blogCreatDate", required = false) String sortBy,
                                     @RequestParam(defaultValue = "asc", required = false) String sortType){

        Sort sort = sortType.equals("asc") ? Sort.by(sortBy) : Sort.by(sortBy).descending() ;
        Pageable pageable = PageRequest.of(pageIndex -1 , pageSize, sort) ;
        Response response = blogService.getAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }



}
