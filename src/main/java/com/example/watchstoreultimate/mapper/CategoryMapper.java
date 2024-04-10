package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request) ;
    void updCategory(@MappingTarget Category category , CategoryRequest request) ;
}
