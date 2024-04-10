package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.constant.ErrorValid;
import com.example.watchstoreultimate.entity.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductRequest {
    @Size(min = 4 , message = ErrorValid.ERR_PRODUCT_NAME_VALID)
    String productName ;
    @Min(value = 1 , message = "Giá sản phẩm không được nhỏ hơn 1")
    int productPrice ;
    @Builder.Default
    LocalDate productSaleDate = LocalDate.now();
    @Min(value = 1 , message = "Số lượng sản phẩm lớn hơn 0")
    int productQuantity;
    @Min(value = 0 , message = "Phần trăm giảm giá của sản phẩm phải lớn hơn hoặc bằng 0")
        @Max(value = 100 , message = "Phần trăm giảm giá của sản phẩm nhỏ hơn bằng 100")
            @Builder.Default
    int productPriceReduction = 0;
    @Builder.Default
    boolean productAvailable = true;
    int brandId ;
}
