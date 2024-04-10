package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.ProductRequest;
import com.example.watchstoreultimate.entity.Product;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T15:12:01+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productName( request.getProductName() );
        product.productPrice( request.getProductPrice() );
        if ( request.getProductSaleDate() != null ) {
            product.productSaleDate( Date.from( request.getProductSaleDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        product.productQuantity( request.getProductQuantity() );
        product.productPriceReduction( request.getProductPriceReduction() );
        product.productAvailable( request.isProductAvailable() );

        return product.build();
    }

    @Override
    public void updProduct(Product product, ProductRequest request) {
        if ( request == null ) {
            return;
        }

        product.setProductName( request.getProductName() );
        product.setProductPrice( request.getProductPrice() );
        if ( request.getProductSaleDate() != null ) {
            product.setProductSaleDate( Date.from( request.getProductSaleDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        else {
            product.setProductSaleDate( null );
        }
        product.setProductQuantity( request.getProductQuantity() );
        product.setProductPriceReduction( request.getProductPriceReduction() );
        product.setProductAvailable( request.isProductAvailable() );
    }
}
