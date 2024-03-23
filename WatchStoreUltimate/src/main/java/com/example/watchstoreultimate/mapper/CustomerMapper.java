package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request) ;
}
