package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CommentRequest;
import com.example.watchstoreultimate.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest request) ;
    void updComment(@MappingTarget Comment comment , CommentRequest commentRequest) ;
}
