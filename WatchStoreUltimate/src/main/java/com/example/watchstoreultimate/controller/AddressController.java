package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.AddressURL.PRE_FIX)
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAddress(@RequestBody@Valid AddressRequest request){
        Response response= addressService.addAddress(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.AddressURL.UPDATE_ADDRESS, method = RequestMethod.PUT)
    public ResponseEntity<?> updAddress(@PathVariable int addressId,
                                        @RequestBody @Valid AddressRequest request){
        Response response = addressService.updAddress(addressId , request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.AddressURL.FIND_BY_ID , method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int addressId){
        Response response = addressService.findById(addressId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.AddressURL.FIND_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> findByCustomerId(@PathVariable int customerId){
        Response response = addressService.findByCustomerId(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

}
