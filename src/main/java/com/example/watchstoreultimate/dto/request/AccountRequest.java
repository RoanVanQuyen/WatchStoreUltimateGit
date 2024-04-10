package com.example.watchstoreultimate.dto.request;

import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {
    @Size(min = 5 , message = "Tên đăng nhập chứa ít nhất 5 kí tự")
    String userName ;
    @Size(min = 8 , message = "Mật khẩu cần chứa ít nhất 8 kí tự ")
    String password ;
    int customerId ;
}
