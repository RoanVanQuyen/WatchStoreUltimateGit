package com.example.watchstoreultimate.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder @Data
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int addressId ;
    @Column(columnDefinition = "varchar(50)" ,nullable = false)
    String addressCommune ;
    @Column(columnDefinition = "varchar(50)" ,nullable = false)
    String addressDistrict ;
    @Column(columnDefinition = "varchar(50)" ,nullable = false)
    String addressProvince ;
    @Column(columnDefinition = "varchar(70)")
    String addressDetails ;
    @OneToOne @JoinColumn(name = "customerId")
    Customer customer ;
}
