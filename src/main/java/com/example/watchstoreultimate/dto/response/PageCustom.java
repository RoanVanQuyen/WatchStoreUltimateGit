package com.example.watchstoreultimate.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data@Builder@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom <T> {
    int pageIndex ;
    int pageElement ;
    int pageSize ;
    List<T> content ;
    String sort ;
}
