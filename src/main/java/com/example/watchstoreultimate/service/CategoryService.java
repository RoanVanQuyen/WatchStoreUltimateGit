package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    static final boolean CATEGORY_AVAILABLE_NOT = false ;
    static final boolean CATEGORY_AVAILABLE = true ;

    Response findAll(Pageable pageable) ;
    Response findCategoryById(Integer request) ;
    Response addCategory(CategoryRequest request) ;
    Response delCategory(List<Integer> request) ;
    Response reDelCategory(List<Integer> request_ids) ;
    Response updCategory(int categoryId , CategoryRequest category) ;
}
