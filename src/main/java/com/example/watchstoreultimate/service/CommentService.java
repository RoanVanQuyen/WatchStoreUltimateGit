package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.CommentRequest;
import com.example.watchstoreultimate.dto.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    final static boolean COMMENT_AVAILABLE = true ;
    Response addComment(CommentRequest request) ;
    Response updComment(int commentId , CommentRequest request) ;
    Response delComment(List<Integer> commentIds) ;
    Response getCommentByProduct(int productId, Pageable pageable) ;
    Response getCommentByCustomer(int customerId , Pageable pageable) ;
    Response getCommentByCommentParent(int commentParentId, Pageable pageable) ;
}
