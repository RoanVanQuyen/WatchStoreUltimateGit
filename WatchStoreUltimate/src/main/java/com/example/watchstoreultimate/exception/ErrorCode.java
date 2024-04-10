package com.example.watchstoreultimate.exception;

import com.example.watchstoreultimate.constant.ErrorResponseCode;
import com.example.watchstoreultimate.constant.ErrorResponseMessage;
import com.example.watchstoreultimate.constant.ErrorValid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    NAME_EXISTED(             ErrorResponseCode.NAME_EXISTED ,               ErrorResponseMessage.NAME_EXISTED)
    , CART_EXISTED(           ErrorResponseCode.Cart.ERR_CART_EXISTED ,      ErrorResponseMessage.Cart.ERR_CART_EXISTED)
    , CART_NOT_EXISTED(       ErrorResponseCode.Cart.ERR_CART_NOT_EXISTED ,  ErrorResponseMessage.Cart.ERR_CART_NOT_EXISTED)
    , PAYMENT_FAILED(         ErrorResponseCode.Cart.PAYMENT_FAILED ,        ErrorResponseMessage.Cart.PAYMENT_FAILED)
    , USERNAME_EXISTED(       ErrorResponseCode.Account.USERNAME_EXISTED,    ErrorResponseMessage.Account.USERNAME_EXISTED)
    , USERNAME_NOT_FOUND(     ErrorResponseCode.Account.USERNAME_NOT_FOUND , ErrorResponseMessage.Account.USERNAME_NOT_FOUND)
    , EMAIL_EXISTED(          ErrorResponseCode.Customer.EMAIL_EXISTED ,     ErrorResponseMessage.Customer.EMAIL_EXISTED)
    , PHONE_EXISTED(          ErrorResponseCode.Customer.PHONE_EXISTED ,     ErrorResponseMessage.Customer.PHONE_EXISTED)
    , EMAIL_NO_CHANGE(        ErrorResponseCode.Customer.EMAIL_NO_CHANGE ,   ErrorResponseMessage.Customer.EMAIL_NO_CHANGE)
    , ERR_ID_NOT_FOUND(       ErrorResponseCode.ERR_ID_NOT_FOUND,            ErrorResponseMessage.ERR_ID_NOT_FOUND)
    , ERR_TOKEN(              ErrorResponseCode.Account.TOKEN_ERR ,          ErrorResponseMessage.Account.ERR_TOKEN)
    , ERR_EMAIL_CODE_NOT_ACC( ErrorResponseCode.Customer.ERR_AUTH_EMAIL ,    ErrorResponseMessage.Customer.ERR_AUTH_EMAIL)
    , ERR_ADDRESS_EXISTED_FOR_CUSTOMER(ErrorResponseCode.Address.ERR_ADDRESS_EXISTED_FOR_CUSTOMER ,    ErrorResponseMessage.Address.ERR_ADDRESS_EXISTED_FOR_CUSTOMER)

    , ERR_BRAND_NAME_VALID(100 ,        ErrorValid.ERR_BRAND_NAME_VALID)
    , ERR_BRAND_DETAILS_VALID(99 ,      ErrorValid.ERR_BRAND_DETAILS_VALID)
    , ERR_CUSTOMER_EMAIL_VALID(112,     ErrorValid.ERR_CUSTOMER_EMAIL_VALID)
    , ERR_CUSTOMER_PHONE_VALID(113 ,    ErrorValid.ERR_CUSTOMER_PHONE_VALID)
    , ERR_VALID(101 , "Chưa bổ sung")
    , ERR_VALID_ARGUMENT(90 , "")
    ;
    int code ;
    String message ;

    public String getMessage(){
        return message ;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
