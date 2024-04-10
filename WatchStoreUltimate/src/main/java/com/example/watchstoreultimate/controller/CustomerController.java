package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CustomerRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.CustomerURL.PRE_FIX)
@Tag( name = "CUSTOMER API" ,  description = "REST API CUSTOMER FOR FRONT-END DEVELOPER")
public class CustomerController {
    @Autowired
    CustomerService customerService ;


    @Operation(summary = "GET CUSTOMER")
    @RequestMapping(value = UrlConstant.CustomerURL.FIND_ALL , method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@RequestParam(defaultValue = "1", required = false) int pageIndex ,
                                         @RequestParam(defaultValue = "9" ,required = false) int pageElement ,
                                         @RequestParam(defaultValue = "accountCreatDate" , required = false) String sortBy){
        Pageable pageable = PageRequest.of(pageIndex - 1  , pageElement , Sort.by(sortBy).descending()) ;
        Response response = customerService.getCustomer(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "GET CUSTOMER BY ID")
    @ApiResponse(responseCode = ErrorResponseCode.ERR_ID_NOT_FOUND  + "" , description = ErrorResponseMessage.ERR_ID_NOT_FOUND)
    @RequestMapping(value = UrlConstant.CustomerURL.FIND_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<?> findCustomerById(@PathVariable int customerId){
        Response response = customerService.findCustomerById(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "ADD CUSTOMER")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_EMAIL_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_EMAIL_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_PHONE_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_PHONE_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.EMAIL_EXISTED+ "" , description = ErrorResponseMessage.Customer.EMAIL_EXISTED)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.PHONE_EXISTED+ "" , description = ErrorResponseMessage.Customer.PHONE_EXISTED)
            }
    )
    @RequestMapping(value = UrlConstant.NONE_AUTH , method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerRequest request){
        Response response = customerService.addCustomer(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(summary = "UPDATE INFO CUSTOMER")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_EMAIL_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_EMAIL_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_PHONE_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_PHONE_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.EMAIL_NO_CHANGE+ "" , description = ErrorResponseMessage.Customer.EMAIL_NO_CHANGE)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.PHONE_EXISTED+ "" , description = ErrorResponseMessage.Customer.PHONE_EXISTED)
            }
    )
    @RequestMapping(value = UrlConstant.ROLE_USER , method = RequestMethod.PUT)
    public ResponseEntity<?> updCustomer(@RequestBody @Valid Customer customer){
        Response response = customerService.updCustomer(customer) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "DELETE CUSTOMER FROM MANAGER")
    @RequestMapping(value = UrlConstant.CustomerURL.M_DEL_CUSTOMER,method = RequestMethod.DELETE)
    public ResponseEntity<?> delCustomer(@PathVariable List<Integer> customerIds){
        Response response = customerService.delCustomer(customerIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "DELETE CUSTOMER FROM ADMIN")
    @RequestMapping(value = UrlConstant.CustomerURL.A_DEL_CUSTOMER, method = RequestMethod.DELETE)
    public ResponseEntity<?> delCustomerNoChange(@PathVariable List<Integer> customerIds){
        Response response = customerService.delCustomerNoChange(customerIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "SEND CODE FROM CUSTOMER EMAIL")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_EMAIL_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_EMAIL_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.ERR_CUSTOMER_PHONE_VALID + "" , description = ErrorResponseMessage.Customer.ERR_CUSTOMER_PHONE_VALID)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.EMAIL_EXISTED+ "" , description = ErrorResponseMessage.Customer.EMAIL_EXISTED)
                    , @ApiResponse(responseCode = ErrorResponseCode.Customer.PHONE_EXISTED+ "" , description = ErrorResponseMessage.Customer.PHONE_EXISTED)
            }
    )
    @RequestMapping(value = UrlConstant.CustomerURL.SEND_REQUEST_CONFIRM_EMAIL ,method = RequestMethod.POST)
    public ResponseEntity<?> sendMail(@RequestBody @Valid CustomerRequest request) throws MessagingException, UnsupportedEncodingException {
        Response response = customerService.sendCode(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


    @Operation(summary = "CONFIRM CODE FROM CUSTOMER EMAIL")
    @RequestMapping(value = UrlConstant.CustomerURL.AUTH_REQUEST_CONFIRM_EMAIL, method = RequestMethod.POST)
    public ResponseEntity<?> authCode(@RequestBody @Valid CustomerRequest request,
                                      @PathVariable int code){
        Response response = customerService.authCode(request , code) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
