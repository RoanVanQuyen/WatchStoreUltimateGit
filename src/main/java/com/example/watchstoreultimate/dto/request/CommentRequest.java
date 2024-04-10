package com.example.watchstoreultimate.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data @NoArgsConstructor@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
    String commentContent ;
    @Builder.Default
    LocalDateTime commentDate = LocalDateTime.now();
    int customerId ;
    int productId ;
    int commentParentId;
    @Builder.Default
    boolean commentAvailable = true;

}
