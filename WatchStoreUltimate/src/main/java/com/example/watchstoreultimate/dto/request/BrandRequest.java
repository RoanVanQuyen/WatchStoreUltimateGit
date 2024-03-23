package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.constant.ErrorValid;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder @Data
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandRequest {
    @Size(min = 4 , message = ErrorValid.ERR_BRAND_NAME_VALID)
    String brandName ;
    @Size(min = 100 , message = ErrorValid.ERR_BRAND_DETAILS_VALID)
    String brandDetails ;
    @Builder.Default
    boolean brandAvailable = true;
}
