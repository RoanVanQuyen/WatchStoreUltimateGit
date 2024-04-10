package com.example.watchstoreultimate.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data@Builder
@AllArgsConstructor@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
    int customerId ;
    int productId ;
    @Builder.Default
    @Min(value = 1, message = "Số lượng sản phẩm trong giỏ hàng phải lớn hơn 0")
    int cartQuantity = 1;
    @Builder.Default
    LocalDate cart_add_date = LocalDate.now();
}
