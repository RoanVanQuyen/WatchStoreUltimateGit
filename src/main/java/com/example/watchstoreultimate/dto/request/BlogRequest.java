package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.constant.ErrorValid;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogRequest {
    @Size(min = 10 , message = ErrorValid.ERR_BLOG_NAME_VALID)
    String blogName ;
    @Size(min = 100 , message = ErrorValid.ERR_BLOG_CONTENT_VALID)
    String blogContent ;
    int customerId ;
}
