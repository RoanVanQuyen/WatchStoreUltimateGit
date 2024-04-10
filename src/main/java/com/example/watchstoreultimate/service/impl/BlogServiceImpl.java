package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.BlogRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Blog;
import com.example.watchstoreultimate.entity.Comment;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.BlogMapper;
import com.example.watchstoreultimate.repository.BlogRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.service.BlogService;
import com.example.watchstoreultimate.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository ;
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    BlogMapper blogMapper ;
    @Autowired
    RedisService redisService ;
    final static String KEY = "blog" ;
    @Override
    public Response addBlog(BlogRequest request) {
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(), true).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        Blog blog = blogMapper.toBlog(request) ;
        blog.setCustomer(customer);
        blogRepository.save(blog) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(201)
                .message("add blog success")
                .result(blog)
                .build();
    }

    @Override
    public Response updBlog(int blogId, BlogRequest request) {
        Blog blog = blogRepository.findBlogByBlogIdAndAvailable(blogId,BLOG_AVAILABLE).orElseThrow(
                ()  ->  new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        blogMapper.updBlog(blog,request);
        blogRepository.save(blog) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(201)
                .message("add blog success")
                .result(blog)
                .build();
    }

    @Override
    public Response delBlog(List<Integer> blogIds) {
        List<Blog> blogs = new ArrayList<>() ;
        for(int i = 0 ; i < blogIds.size();i++){
            Optional<Blog> blog = blogRepository.findBlogByBlogIdAndAvailable(blogIds.get(i) , BLOG_AVAILABLE) ;
            if(blog.isPresent()){
                blog.get().setAvailable(NON_BLOG_AVAILABLE);
                blogRepository.save(blog.get()) ;
                blogs.add(blog.get()) ;
            }
        }
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(blogs)
                .message("Delete success")
                .build() ;
    }

    @Override
    public Response getById(int blogId) {
        String field= "blogId: " + blogId ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Blog blog = blogRepository.findBlogByBlogIdAndAvailable(blogId, BLOG_AVAILABLE).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            blog.setView(blog.getView() + 1);
            blogRepository.save(blog);
            response =  Response.builder()
                    .code(200)
                    .result(blog)
                    .message("")
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return response ;
    }

    @Override
    public Response getByCustomer(int customerId, Pageable pageable) {
        String field = customerId +":" + pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY, field);
        if(response == null) {
            Page<Blog> page = blogRepository.getBlogByCustomer(customerId, pageable);
            PageCustom<Blog> pageCustom = PageCustom.<Blog>builder()
                    .pageIndex(page.getNumber() + 1)
                    .pageElement(page.getSize())
                    .pageSize(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response = Response.builder()
                    .code(200)
                    .message("GET BLOG BY CUSTOMER SUCCESS")
                    .result(pageCustom)
                    .build();
            redisService.hashSet(KEY, field , response);
        }
        return response ;
    }

    @Override
    public Response getAll(Pageable pageable) {
        String field = pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Page<Blog> page = blogRepository.getAllByAvailable(BLOG_AVAILABLE, pageable);
            PageCustom<Blog> pageCustom = PageCustom.<Blog>builder()
                    .pageIndex(page.getNumber() + 1)
                    .pageElement(page.getSize())
                    .pageSize(page.getTotalPages())
                    .content(page.getContent())
                    .sort(page.getSort().toString())
                    .build();
            response =  Response.builder()
                    .code(200)
                    .message("GET ALL BLOG BY SUCCESS")
                    .result(pageCustom)
                    .build();
            redisService.hashSet(KEY , field , response);
        }
        return  response ;
    }
}
