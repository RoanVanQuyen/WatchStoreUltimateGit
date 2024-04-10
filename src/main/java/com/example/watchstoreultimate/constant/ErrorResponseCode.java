package com.example.watchstoreultimate.constant;

public class ErrorResponseCode {
    public final static int ERR_ID_NOT_FOUND = 1110 ;
    public final static int NAME_EXISTED = 1004 ;

    public static class Account{
        public final static int USERNAME_EXISTED = 1001 ;
        public final static int USERNAME_NOT_FOUND = 1002 ;
        public final static int TOKEN_ERR = 403 ;
    }
    public static class Address{
        public final static int ERR_ADDRESS_EXISTED_FOR_CUSTOMER = 1003 ;
    }

    public static class Product{

    }

    public static class Category{
        public final static int NAME_EXISTED = 1004 ;
    }

    public static class Customer{
        public final static int  ERR_CUSTOMER_EMAIL_VALID = 1005 ;
        public final static int  ERR_CUSTOMER_PHONE_VALID = 1006 ;
        public final static int EMAIL_EXISTED = 1007 ;
        public final static int PHONE_EXISTED =  1008 ;
        public final static int EMAIL_NO_CHANGE = 1009 ;
        public final static int ERR_AUTH_EMAIL = 1010 ;
    }
    public static class Cart{
        public final static int ERR_CART_EXISTED = 1011 ;
        public final static int ERR_CART_NOT_EXISTED = 1012 ;
        public final static int PAYMENT_FAILED = 1013 ;
    }
}
