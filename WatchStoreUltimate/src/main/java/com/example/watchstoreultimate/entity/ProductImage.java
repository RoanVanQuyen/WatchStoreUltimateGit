package com.example.watchstoreultimate.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productImageId ;
    @Lob @Column(columnDefinition = "longblob" , nullable = false)
    byte[] imageData ;
    @Column(columnDefinition = "varchar(60)" , unique = true , nullable = false)
    String imageName ;
    @Column(columnDefinition = "varchar(30)" , nullable = false)
    String imageType ;
    @ManyToOne @JoinColumn(name = "productId")
    Product product ;
}
