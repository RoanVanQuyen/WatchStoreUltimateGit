package com.example.watchstoreultimate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data@Hidden
@NoArgsConstructor @AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId ;
    @Column(columnDefinition = "varchar(150)" ,unique = true , nullable = false)
    String productName ;
    @Min(value = 1 , message = "Giá sản phẩm không được nhỏ hơn 1")
    int productPrice ;
    @Builder.Default
            @Column(columnDefinition = "DATE")
    Date productSaleDate = new Date(System.currentTimeMillis());
    @Min(value = 1 , message = "Số lượng sản phẩm lớn hơn 0")
    int productQuantity;
    @Min(value = 0 , message = "Phần trăm giảm giá của sản phẩm phải lớn hơn hoặc bằng 0")
            @Max(value = 100 , message = "Phần trăm giảm giá của sản phẩm nhỏ hơn bằng 100")
    int productPriceReduction ;
    @Builder.Default
    boolean productAvailable = true;
    @ManyToOne @JoinColumn(name = "brandId")
    Brand brand ;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
            @JsonIgnore
    List<ProductImage> productImages ;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
            @JsonIgnore
    List<Cart> carts ;
    @OneToOne(mappedBy = "product")
            @JsonIgnore
    ProductDetails productDetails ;
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
            @JsonIgnore
    List<PurchaseHistory> purchaseHistories ;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
            @JsonIgnore
    List<Comment> comments ;
}
