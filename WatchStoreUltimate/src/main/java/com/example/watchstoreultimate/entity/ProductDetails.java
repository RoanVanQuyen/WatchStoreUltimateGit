package com.example.watchstoreultimate.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data @Builder@Hidden
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productDetailsId ;
    @Column(columnDefinition = "varchar(40)")
    String shellMaterial ;
    @Column(columnDefinition = "varchar(40)")
    String glassMaterial ;
    @Column(columnDefinition = "varchar(40)")
    String wireMaterial ;
    @Column(columnDefinition = "varchar(40)")
    String waterResistance;
    @Column(columnDefinition = "varchar(40)")
    String shape ;
    @Column(columnDefinition = "varchar(40)")
    String faceSize ;
    @Column(columnDefinition = "varchar(40)")
    String shellColor ;
    @Column(columnDefinition = "varchar(40)")
    String faceColor ;
    @Column(columnDefinition = "varchar(40)")
    String origin ;
    @Column(columnDefinition = "varchar(40)")
    String style ;
    @OneToOne @JoinColumn(name = "productId")
    Product product ;
}
