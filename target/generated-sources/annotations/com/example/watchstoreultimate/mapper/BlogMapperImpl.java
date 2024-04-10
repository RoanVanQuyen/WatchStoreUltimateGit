package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.BlogRequest;
import com.example.watchstoreultimate.entity.Blog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public Blog toBlog(BlogRequest request) {
        if ( request == null ) {
            return null;
        }

        Blog.BlogBuilder blog = Blog.builder();

        blog.blogName( request.getBlogName() );
        blog.blogContent( request.getBlogContent() );

        return blog.build();
    }

    @Override
    public void updBlog(Blog blog, BlogRequest request) {
        if ( request == null ) {
            return;
        }

        blog.setBlogName( request.getBlogName() );
        blog.setBlogContent( request.getBlogContent() );
    }
}
