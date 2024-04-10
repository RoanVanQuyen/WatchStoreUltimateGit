package com.example.watchstoreultimate.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Hidden
@Entity
@Data
@Builder@NoArgsConstructor@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int purchaseHistoryId  ;
    @ManyToOne @JoinColumn(name = "productId")
    Product product ;
    @ManyToOne @JoinColumn(name = "customerId")
            Customer customer ;
    int quantity ;
    @Builder.Default
    LocalDate purchaseHistoryDay = LocalDate.now() ;
    @Column(columnDefinition = "varchar(50)")
    String paymentMethod  ;
    int priceSold ;
}
