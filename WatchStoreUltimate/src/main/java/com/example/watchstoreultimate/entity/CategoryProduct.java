package com.example.watchstoreultimate.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @FieldDefaults(level = AccessLevel.PRIVATE)@Hidden
@IdClass(com.example.watchstoreultimate.idclass.CategoryProduct.class)
@Builder@Data
@AllArgsConstructor @NoArgsConstructor
public class CategoryProduct {
    @Id @ManyToOne @JoinColumn(name = "categoryId")
    Category category ;
    @Id @ManyToOne @JoinColumn(name = "productId")
    Product product ;
}
