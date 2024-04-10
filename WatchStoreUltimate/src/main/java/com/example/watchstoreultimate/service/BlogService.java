package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.BlogRequest;
import com.example.watchstoreultimate.dto.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    final static boolean BLOG_AVAILABLE = true ;
    final static boolean NON_BLOG_AVAILABLE = false ;

    Response addBlog(BlogRequest request) ;
    Response updBlog(int blogId , BlogRequest request) ;
    Response delBlog(List<Integer> blogIds) ;
    Response getById(int blogId) ;
    Response getByCustomer(int customerId, Pageable pageable) ;
    Response getAll(Pageable pageable) ;
}
