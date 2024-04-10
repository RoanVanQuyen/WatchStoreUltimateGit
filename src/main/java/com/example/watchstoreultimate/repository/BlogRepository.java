package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Blog;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Hidden
public interface BlogRepository extends JpaRepository<Blog , Integer> {
    Optional<Blog> findBlogByBlogIdAndAvailable(int blogId , boolean Available) ;
    @Query(value = "FROM Blog b " +
            "WHERE b.customer.customerId= :customerId " +
            "AND " +
            "b.available = true ")
    Page<Blog> getBlogByCustomer(int customerId , Pageable pageable) ;

    Page<Blog> getAllByAvailable(boolean Available , Pageable pageable)  ;
}
