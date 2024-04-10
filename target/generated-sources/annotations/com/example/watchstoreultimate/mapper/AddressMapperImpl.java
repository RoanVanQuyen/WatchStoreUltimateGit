package com.example.watchstoreultimate.mapper;

import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T15:09:10+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressRequest request) {
        if ( request == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.addressCommune( request.getAddressCommune() );
        address.addressDistrict( request.getAddressDistrict() );
        address.addressProvince( request.getAddressProvince() );
        address.addressDetails( request.getAddressDetails() );

        return address.build();
    }

    @Override
    public void updAddress(Address address, AddressRequest request) {
        if ( request == null ) {
            return;
        }

        address.setAddressCommune( request.getAddressCommune() );
        address.setAddressDistrict( request.getAddressDistrict() );
        address.setAddressProvince( request.getAddressProvince() );
        address.setAddressDetails( request.getAddressDetails() );
    }
}
