package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailsRequest {
    String shellMaterial ;
    String glassMaterial ;
    String wireMaterial ;
    String waterResistance;
    String shape ;
    String faceSize ;
    String shellColor ;
    String faceColor ;
    String origin ;
    String style ;
    int productId ;
}
