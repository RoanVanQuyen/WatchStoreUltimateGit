package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request) ;
    void updProduct(@MappingTarget Product product , ProductRequest request) ;
}
