package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Comment;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Hidden
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("FROM Comment  c " +
            "WHERE c.product.productId=:productId " +
            "AND c.commentAvailable = true " )
    Page<Comment> getCommentsByProduct(int productId  , Pageable pageable) ;

    @Query("FROM  Comment  c " +
            "WHERE  c.customer.customerId =:customerId " +
            "AND c.commentAvailable = true")
    Page<Comment> getCommentsByCustomer(int customerId , Pageable pageable) ;


    @Query("FROM  Comment  c " +
            "WHERE  c.commentParent.commentId=:commentParentId " +
            "AND c.commentAvailable=true ")
    Page<Comment> getCommentsByCommentParent(int commentParentId , Pageable pageable) ;

}
