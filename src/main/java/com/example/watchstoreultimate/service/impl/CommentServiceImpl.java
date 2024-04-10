package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CommentRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.entity.Comment;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.CommentMapper;
import com.example.watchstoreultimate.repository.CommentRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.service.CommentService;
import com.example.watchstoreultimate.service.RedisService;
import io.swagger.v3.oas.annotations.Hidden;
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
public class CommentServiceImpl implements CommentService {
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    CommentMapper commentMapper ;
    @Autowired
    CommentRepository commentRepository ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    RedisService redisService  ;
    static final String KEY = "comment" ;
    @Override
    public Response addComment(CommentRequest request) {
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(), COMMENT_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Product product = productRepository.findByProductIdAndProductAvailable(request.getProductId(),  COMMENT_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        Optional<Comment> commentParent = commentRepository.findById(request.getCommentParentId()) ;
        Comment comment = commentMapper.toComment(request) ;
        comment.setCustomer(customer);
        comment.setProduct(product);
        comment.setCommentParent(commentParent.isPresent() ? commentParent.get() : null);
        commentRepository.save(comment) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(comment)
                .message("Add comment success")
                .build() ;
    }

    @Override
    public Response updComment(int commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(), COMMENT_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Product product = productRepository.findByProductIdAndProductAvailable(request.getProductId(),  COMMENT_AVAILABLE).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        Optional<Comment> commentParent = commentRepository.findById(request.getCommentParentId()) ;
        commentMapper.updComment(comment , request);
        comment.setCustomer(customer);
        comment.setProduct(product);
        comment.setCommentParent(commentParent.isPresent() ? commentParent.get() : null);
        commentRepository.save(comment) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(comment)
                .message("Update comment success")
                .build() ;
    }

    @Override
    public Response delComment(List<Integer> commentIds) {
        List<Comment> comments = new ArrayList<>() ;
        for(Integer x : commentIds){
            Optional<Comment> commentOptional = commentRepository.findById(x) ;
            if(commentOptional.isPresent()){
                commentOptional.get().setCommentAvailable(false);
                comments.add(commentOptional.get()) ;
                commentRepository.save(commentOptional.get()) ;
            }
        }
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(comments)
                .message("Delete success")
                .build() ;
    }
    private PageCustom<Comment> getPageCustomer(Page<Comment> page){
        PageCustom<Comment> pageCustom = PageCustom.<Comment>builder()
                .pageIndex(page.getNumber() + 1)
                .pageElement(page.getSize())
                .pageSize(page.getTotalPages())
                .content(page.getContent())
                .sort(page.getSort().toString())
                .build();
        return pageCustom ;
    }

    @Override
    public Response getCommentByProduct(int productId, Pageable pageable) {
        String field = "productId: " + productId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY, field);
        if(response == null) {
            Page<Comment> page = commentRepository.getCommentsByProduct(productId, pageable);
            PageCustom<Comment> pageCustom = getPageCustomer(page);

            response = Response.builder()
                    .code(200).result(pageCustom)
                    .message("Get comments by product success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response getCommentByCustomer(int customerId, Pageable pageable) {
        String field = "customerId: " + customerId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY, field) ;
        if(response ==null) {
            Page<Comment> page = commentRepository.getCommentsByCustomer(customerId, pageable);
            PageCustom<Comment> pageCustom = getPageCustomer(page);
            response =  Response.builder()
                    .code(200)
                    .result(pageCustom)
                    .message("Get comments by customer success")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return  response ;
    }

    @Override
    public Response getCommentByCommentParent(int commentParentId, Pageable pageable) {
        String field = "commentParentId: " + commentParentId + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field) ;
        if(response == null) {
            Page<Comment> page = commentRepository.getCommentsByCommentParent(commentParentId, pageable);
            PageCustom<Comment> pageCustom = getPageCustomer(page);
            response =  Response.builder()
                    .code(200).result(pageCustom)
                    .message("Get comments by comment success")
                    .build();
        }
        return  response  ;
    }
}
