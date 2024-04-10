package com.example.watchstoreultimate.entity;

import com.example.watchstoreultimate.idclass.CustomerProduct;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity @IdClass(CustomerProduct.class)@Hidden
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Cart {
    @Id @ManyToOne @JoinColumn(name = "customerId")
    Customer customer ;
    @Id @ManyToOne @JoinColumn(name = "productId")
    Product product ;
    @Min(value = 1, message = "Số lượng sản phẩm trong giỏ hàng phải lớn hơn 0")
    int cartQuantity;
    @Builder.Default
            @Column(columnDefinition = "DATE")
    LocalDate cart_add_date = LocalDate.now();
}
