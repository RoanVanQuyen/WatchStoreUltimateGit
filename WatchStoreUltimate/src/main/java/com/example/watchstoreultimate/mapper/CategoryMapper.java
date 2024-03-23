package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request) ;
}
