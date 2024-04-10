package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CommentRequest;
import com.example.watchstoreultimate.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toComment(CommentRequest request) {
        if ( request == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentContent( request.getCommentContent() );
        comment.commentDate( request.getCommentDate() );
        comment.commentAvailable( request.isCommentAvailable() );

        return comment.build();
    }

    @Override
    public void updComment(Comment comment, CommentRequest commentRequest) {
        if ( commentRequest == null ) {
            return;
        }

        comment.setCommentContent( commentRequest.getCommentContent() );
        comment.setCommentDate( commentRequest.getCommentDate() );
        comment.setCommentAvailable( commentRequest.isCommentAvailable() );
    }
}
