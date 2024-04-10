package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CommentRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.CommentURL.PRE_FIX)
@Tag( name = "COMMENT API" ,  description = "REST API COMMENT FOR FRONT-END DEVELOPER")
public class CommentController {

    @Autowired
    private CommentService commentService ;


    @RequestMapping(value = UrlConstant.ROLE_USER ,method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody @Valid CommentRequest request){
        Response response = commentService.addComment(request);
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CommentURL.U_UPD_COMMENT, method = RequestMethod.PUT)
    public ResponseEntity<?> upDComment(@PathVariable int commentId , @RequestBody @Valid CommentRequest request){
        Response response = commentService.updComment(commentId,request);
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CommentURL.U_DEL_COMMENT, method = RequestMethod.DELETE)
    public ResponseEntity<?> delComments(@PathVariable List<Integer> commentIds){
        Response response = commentService.delComment(commentIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CommentURL.FIND_BY_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> getCommentByProduct(@PathVariable int productId ,
                                                 @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                 @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Sort sort = Sort.by("commentDate").descending() ;
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement, sort) ;
        Response response = commentService.getCommentByProduct(productId, pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @RequestMapping(value= UrlConstant.CommentURL.FIND_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> getCommentByCustomer(@PathVariable int customerId ,
                                                 @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                 @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement) ;
        Response response = commentService.getCommentByCustomer(customerId , pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CommentURL.FIND_BY_COMMENT ,method = RequestMethod.GET)
    public ResponseEntity<?> getCommentByComment(@PathVariable int commentParentId ,
                                                 @RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                                 @RequestParam(defaultValue = "5" , required = false) int pageElement){
        Pageable pageable = PageRequest.of(pageIndex -1 , pageElement) ;
        Response response = commentService.getCommentByCommentParent(commentParentId, pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
