package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request) ;
}
