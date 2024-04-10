package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.constant.ErrorValid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @Size(min = 4 , message = ErrorValid.ERR_CATEGORY_NAME_VALID)
    String categoryName ;
    @Builder.Default
    boolean categoryAvailable = true;
}
