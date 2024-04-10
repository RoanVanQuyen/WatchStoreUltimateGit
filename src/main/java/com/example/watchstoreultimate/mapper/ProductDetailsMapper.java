package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.ProductDetailsRequest;
import com.example.watchstoreultimate.entity.ProductDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper {
    ProductDetails toProductDetails(ProductDetailsRequest request) ;
}
