package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.ProductDetailsRequest;
import com.example.watchstoreultimate.entity.ProductDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ProductDetailsMapperImpl implements ProductDetailsMapper {

    @Override
    public ProductDetails toProductDetails(ProductDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductDetails.ProductDetailsBuilder productDetails = ProductDetails.builder();

        productDetails.shellMaterial( request.getShellMaterial() );
        productDetails.glassMaterial( request.getGlassMaterial() );
        productDetails.wireMaterial( request.getWireMaterial() );
        productDetails.waterResistance( request.getWaterResistance() );
        productDetails.shape( request.getShape() );
        productDetails.faceSize( request.getFaceSize() );
        productDetails.shellColor( request.getShellColor() );
        productDetails.faceColor( request.getFaceColor() );
        productDetails.origin( request.getOrigin() );
        productDetails.style( request.getStyle() );

        return productDetails.build();
    }
}
