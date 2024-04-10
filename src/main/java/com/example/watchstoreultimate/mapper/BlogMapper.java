package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.BlogRequest;
import com.example.watchstoreultimate.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    Blog toBlog(BlogRequest request) ;
    void updBlog(@MappingTarget Blog blog , BlogRequest request) ;
}
