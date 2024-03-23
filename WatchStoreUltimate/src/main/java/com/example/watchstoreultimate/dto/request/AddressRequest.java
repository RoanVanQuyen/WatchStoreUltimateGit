package com.example.watchstoreultimate.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Data @AllArgsConstructor@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    @Size(min = 1 , message = "Không được bỏ trống thông tin về địa chỉ")
    String addressCommune ;
    @Size(min = 1 , message = "Không được bỏ trống thông tin về địa chỉ")
    String addressDistrict ;
    @Size(min = 1 , message = "Không được bỏ trống thông tin về địa chỉ")
    String addressProvince ;
    String addressDetails ;
    int customerId ;
}
