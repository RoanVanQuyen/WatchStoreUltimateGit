package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Category;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.CategoryMapper;
import com.example.watchstoreultimate.repository.CategoryRepository;
import com.example.watchstoreultimate.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository ;
    @Autowired
    CategoryMapper categoryMapper ;
    @Override
    public Response findAll(Pageable pageable) {
        List<Category> categories = new ArrayList<>() ;
        categories = categoryRepository.findAllByCategoryAvailable(pageable , CATEGORY_AVAILABLE).getContent() ;
        return Response.builder()
                .code(200)
                .result(categories)
                .message("List Category: " + (pageable.getPageNumber() + 1))
                .build();
    }

    @Override
    public Response findCategoryById(Integer request) {
        Category category = categoryRepository.findByCategoryIdAndCategoryAvailable(request, CATEGORY_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(category)
                .message("Find category by category_id success")
                .build();
    }

    @Override
    public Response addCategory(CategoryRequest request) {
        if(categoryRepository.existsCategoryByCategoryName(request.getCategoryName())){
            throw  new AppException(ErrorCode.NAME_EXISTED) ;
        }
        System.out.println(request.toString());
        Category category = categoryMapper.toCategory(request) ;
        return Response.builder()
                .code(200)
                .result(categoryRepository.save(category))
                .message("Success for add category")
                .build();
    }

    @Override
    public Response delCategory(List<Integer> request) {
        List<Category> categories = new ArrayList<>() ;
        for(Integer id : request){
            Optional<Category> categoryOptional =categoryRepository.findById(id) ;
            if(categoryOptional.isPresent()){
                categoryOptional.get().setCategoryAvailable(CATEGORY_AVAILABLE_NOT);
                categoryRepository.save(categoryOptional.get()) ;
                categories.add(categoryOptional.get()) ;
            }
        }
        return Response.builder()
                .code(200)
                .result(categories)
                .message("Success for delete")
                .build();
    }

    @Override
    public Response reDelCategory(List<Integer> request_ids) {
        List<Category> categories = new ArrayList<>() ;
        for(Integer id : request_ids){
            Optional<Category> categoryOptional =categoryRepository.findById(id) ;
            if(categoryOptional.isPresent()){
                categoryOptional.get().setCategoryAvailable(CATEGORY_AVAILABLE);
                categoryRepository.save(categoryOptional.get()) ;
                categories.add(categoryOptional.get()) ;
            }
        }
        return Response.builder()
                .code(200)
                .result(categories)
                .message("Success for delete")
                .build();
    }

    @Override
    public Response updCategory(Category category) {
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw  new AppException(ErrorCode.NAME_EXISTED) ;
        }
        categoryRepository.findById(category.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;

        return Response.builder()
                .code(200)
                .result(categoryRepository.save(category))
                .message("Success")
                .build();
    }
}
