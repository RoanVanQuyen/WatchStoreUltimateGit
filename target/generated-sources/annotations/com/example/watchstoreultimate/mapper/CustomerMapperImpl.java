package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T01:12:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customerName( request.getCustomerName() );
        customer.customerEmail( request.getCustomerEmail() );
        customer.customerPhone( request.getCustomerPhone() );

        return customer.build();
    }
}
