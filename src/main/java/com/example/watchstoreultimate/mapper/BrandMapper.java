package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest request) ;
    void updBrand(@MappingTarget Brand brand , BrandRequest brandRequest) ;
}
