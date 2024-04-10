package com.example.watchstoreultimate.entity;

import com.example.watchstoreultimate.constant.ErrorValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data @Builder@Hidden
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int brandId ;
    @Size(min = 4 , message = ErrorValid.ERR_BRAND_NAME_VALID)
    @Column(columnDefinition = "varchar(150)" , nullable = false, unique = true)
    String brandName ;
    @Size(min = 100 , message = ErrorValid.ERR_BRAND_DETAILS_VALID)
    @Column(columnDefinition = "text" , nullable = false , unique = true)
    String brandDetails ;
    @Builder.Default
            @Column(columnDefinition = "default '1'")
    boolean brandAvailable = true;

    // Mapper
    @OneToMany(mappedBy = "brand" , cascade = CascadeType.ALL)
            @JsonIgnore
    List<Product> products ;
}
