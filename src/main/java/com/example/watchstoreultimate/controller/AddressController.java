package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.AddressRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.AddressURL.PRE_FIX)
@Tag( name = "ADDRESS API" , description = "REST API ADDRESS FOR FRONT-END DEVELOPER ")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "ADD ADDRESS FOR CUSTOMER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
            ,@ApiResponse( responseCode = ErrorResponseCode.Address.ERR_ADDRESS_EXISTED_FOR_CUSTOMER +"" , description = ErrorResponseMessage.Address.ERR_ADDRESS_EXISTED_FOR_CUSTOMER)
    })
    @RequestMapping(value = UrlConstant.ROLE_USER , method = RequestMethod.POST)
    public ResponseEntity<?> addAddress(@RequestBody@Valid AddressRequest request){
        Response response= addressService.addAddress(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "UPDATE ADDRESS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    })
    @RequestMapping(value = UrlConstant.AddressURL.U_UPDATE_ADDRESS, method = RequestMethod.PUT)
    public ResponseEntity<?> updAddress(@PathVariable int addressId,
                                        @RequestBody @Valid AddressRequest request){
        Response response = addressService.updAddress(addressId , request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "GET ADDRESS BY ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    })
    @RequestMapping(value = UrlConstant.AddressURL.FIND_BY_ID , method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable int addressId){
        Response response = addressService.findById(addressId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "GET ADDRESS BY ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND + "", description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    })
    @RequestMapping(value = UrlConstant.AddressURL.FIND_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> findByCustomerId(@PathVariable int customerId){
        Response response = addressService.findByCustomerId(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

}
