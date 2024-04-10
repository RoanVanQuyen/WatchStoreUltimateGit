package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.entity.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toBrand(BrandRequest request) {
        if ( request == null ) {
            return null;
        }

        Brand.BrandBuilder brand = Brand.builder();

        brand.brandName( request.getBrandName() );
        brand.brandDetails( request.getBrandDetails() );
        brand.brandAvailable( request.isBrandAvailable() );

        return brand.build();
    }

    @Override
    public void updBrand(Brand brand, BrandRequest brandRequest) {
        if ( brandRequest == null ) {
            return;
        }

        brand.setBrandName( brandRequest.getBrandName() );
        brand.setBrandDetails( brandRequest.getBrandDetails() );
        brand.setBrandAvailable( brandRequest.isBrandAvailable() );
    }
}
