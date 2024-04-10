package com.example.watchstoreultimate.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductRequest {
    int productId ;
    List<Integer> categoryIds;
}
