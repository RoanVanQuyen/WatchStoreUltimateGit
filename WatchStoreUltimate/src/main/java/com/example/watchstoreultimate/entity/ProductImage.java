package com.example.watchstoreultimate.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder@Hidden
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productImageId ;
    String productImageUrl ;
    @ManyToOne @JoinColumn(name = "productId")
    Product product ;
}
