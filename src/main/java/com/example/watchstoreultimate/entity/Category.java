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
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int categoryId ;
    @Size(min = 4 , message = ErrorValid.ERR_CATEGORY_NAME_VALID)
    @Column(columnDefinition = "varchar(150)" , unique = true , nullable = false)
    String categoryName ;
    @Builder.Default
    boolean categoryAvailable = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
            @JsonIgnore
    List<CategoryProduct> products ;
}
