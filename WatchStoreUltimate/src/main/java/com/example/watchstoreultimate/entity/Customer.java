package com.example.watchstoreultimate.entity;

import com.example.watchstoreultimate.constant.ErrorValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder @Data
@AllArgsConstructor @NoArgsConstructor@Hidden
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId ;
    @Column(columnDefinition = "varchar(40)" ,  nullable = false)
            @Size(min = 1 , message = ErrorValid.ERR_CUSTOMER_NAME_VALID)
    String customerName ;
    @Column(columnDefinition = "varchar(50)  collate 'utf8_bin' " , nullable = false , unique = true)
    String customerEmail ;
    @Column(columnDefinition = "varchar(13)" , nullable = false , unique = true)
            @Size(min = 10 , max = 11 , message = ErrorValid.ERR_CUSTOMER_PHONE_VALID)
    String customerPhone ;
    @Builder.Default
            @Column(columnDefinition = "DATE")
    LocalDate accountCreatDate = LocalDate.now() ;
    @Builder.Default
    boolean customerAvailable =true;

    // mapp
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY ,cascade =  CascadeType.ALL)
            @JsonIgnore
    Account account ;
    @OneToOne(mappedBy = "customer" , fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
            @JsonIgnore
    Address address;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<Cart> carts ;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<PurchaseHistory> purchaseHistories ;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<Comment> comments ;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JsonIgnore
    List<Blog> blogs ;
}
