package com.example.watchstoreultimate.idclass;

import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerProduct implements Serializable {
    Customer customer ;
    Product product ;
}
