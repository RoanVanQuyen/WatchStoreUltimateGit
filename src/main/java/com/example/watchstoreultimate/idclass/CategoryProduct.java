package com.example.watchstoreultimate.idclass;

import com.example.watchstoreultimate.entity.Category;
import com.example.watchstoreultimate.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor @NoArgsConstructor
@Data
public class CategoryProduct implements Serializable {
    Category category ;
    Product product;
}
