package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CategoryRequest;
import com.example.watchstoreultimate.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.categoryName( request.getCategoryName() );
        category.categoryAvailable( request.isCategoryAvailable() );

        return category.build();
    }

    @Override
    public void updCategory(Category category, CategoryRequest request) {
        if ( request == null ) {
            return;
        }

        category.setCategoryName( request.getCategoryName() );
        category.setCategoryAvailable( request.isCategoryAvailable() );
    }
}
