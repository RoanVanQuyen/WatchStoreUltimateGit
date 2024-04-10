package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Category;
import com.example.watchstoreultimate.entity.Comment;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.CategoryMapper;
import com.example.watchstoreultimate.repository.CategoryRepository;
import com.example.watchstoreultimate.service.CategoryService;
import com.example.watchstoreultimate.service.RedisService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    RedisService redisService ;
    final static String KEY = "category" ;
    @Override
    public Response findAll(Pageable pageable) {
        String field = pageable.toString() ;
        Response response  = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Page<Category> page = categoryRepository.findAllByCategoryAvailable(pageable, CATEGORY_AVAILABLE);
            PageCustom<Category> pageCustom = PageCustom.<Category>builder()
                    .pageIndex(page.getNumber() + 1)
                    .pageElement(page.getSize())
                    .pageSize(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response=  Response.builder()
                    .code(200)
                    .result(pageCustom)
                    .message("List Category: " + (pageable.getPageNumber() + 1))
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response findCategoryById(Integer request) {
        String field = "categoryId: " + request ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Category category = categoryRepository.findByCategoryIdAndCategoryAvailable(request, CATEGORY_AVAILABLE).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response =  Response.builder()
                    .code(200)
                    .result(category)
                    .message("Find category by category_id success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response addCategory(CategoryRequest request) {
        if(categoryRepository.existsCategoryByCategoryName(request.getCategoryName())){
            throw  new AppException(ErrorCode.NAME_EXISTED) ;
        }
        System.out.println(request.toString());
        Category category = categoryMapper.toCategory(request) ;
        categoryRepository.save(category) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(category)
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
        redisService.hashDel(KEY);
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
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(categories)
                .message("Success for retrieve info")
                .build();
    }

    @Override
    public Response updCategory(int categoryId , CategoryRequest category) {
        if(categoryRepository.existsCategoryByCategoryName(category.getCategoryName())){
            throw  new AppException(ErrorCode.NAME_EXISTED) ;
        }
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        categoryMapper.updCategory(category1,category);
        categoryRepository.save(category1) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(category1)
                .message("Success")
                .build();
    }
}
