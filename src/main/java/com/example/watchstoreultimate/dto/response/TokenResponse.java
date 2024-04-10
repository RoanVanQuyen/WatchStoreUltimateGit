package com.example.watchstoreultimate.dto.response;

import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Token;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor@NoArgsConstructor
@Data@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenResponse {
    String tokenContent ;
    String refreshToken ;
    String userName ;
    String roleName ;
    Customer customer ;
}
