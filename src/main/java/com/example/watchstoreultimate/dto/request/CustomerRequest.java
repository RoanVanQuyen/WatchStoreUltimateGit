package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.constant.ErrorValid;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    @Size(min = 1 , message = ErrorValid.ERR_CUSTOMER_NAME_VALID)
    String customerName ;
    String customerEmail ;
    @Size(min = 10 , max = 11 , message = ErrorValid.ERR_CUSTOMER_PHONE_VALID)
    String customerPhone ;
}
