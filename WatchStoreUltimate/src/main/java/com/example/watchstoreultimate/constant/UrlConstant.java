package com.example.watchstoreultimate.constant;

public class UrlConstant {
    public static class AccountURL{
        public static final String PRE_FIX = "account" ;
        public static final String SIGN_IN = "sign-in"+ "/{userName}/{password}" ;
        public static final String CHANGE_PASSWORD =  "password/change/{accountId}"  ;
        public static final String FORGET_PASSWORD = "password/forget/{customerEmail}" ;
        public static final String FORGET_PASSWORD_AUTH = "password/forget/auth/{customerEmail}/{code}" ;
    }

    public static class AddressURL{
        public static final String PRE_FIX = "address" ;
        public static final String UPDATE_ADDRESS = "/{addressId}" ;
        public static final String FIND_BY_ID = "id/{addressId}" ;
        public static final String FIND_BY_CUSTOMER ="customer/{customerId}" ;
    }

    public static class BrandURL{
        public static final String PRE_FIX = "brand";
        public static final String FIND_ALL ="find-all" ;
        public static final String FIND_BY_ID = "id/{brandId}" ;
        public static final String DEL_BRAND = "/{brandIds}" ;
        public static final String RE_DEL_BRAND= "/{brandIds}" ;

    }

    public static class CartURL{
        public static final String PRE_FIX = "cart" ;
        public static final String FIND_BY_ID = "/{productId}/{customerId}" ;
        public static final String FIND_BY_PRODUCT = "product/{productId}";
        public static final String FIND_BY_CUSTOMER = "customer/{customerId}" ;
        public static final String PAYMENT = "pay/online" ;
        public static final String PAYMENT_RESULT = "pay/online/result" ;
    }

    public static class CategoryURL{
        public static final String PRE_FIX = "category" ;
        public static final String FIND_BY_ID = "id/{categoryId}" ;
        public static final String FIND_ALL ="find-all" ;
        public static final String DEL_CATEGORY = "/{categoryIds}" ;
        public static final String RE_DEL_CATEGORY = "/{categoryIds}" ;
        public static final String CATEGORY_PRODUCT = "category-product" ;
    }

    public  static  class CustomerURL{
        public static final String PRE_FIX = "customer" ;
        public static final String FIND_BY_ID = "/id/{customerId}" ;
        public static final String FIND_ALL = "find-all" ;
        public static final String DEL_CUSTOMER ="/{customerIds}" ;
        public static final String DEL_CUSTOMER_BY_ADMIN= "/1013/{customerIds}" ;
        public static final String SEND_REQUEST_CONFIRM_EMAIL = "email/code/send" ;
        public static final String AUTH_REQUEST_CONFIRM_EMAIL = "email/code/auth/{code}" ;
    }
    public static class ProductURL{
        public static final String PRE_FIX = "product" ;
        public static final String DEL_PRODUCT ="/{productIds}" ;
        public static final String RE_DEL_PRODUCT ="/{productIds}" ;
        public static final String FIND_BY_ID = "id/{productId}" ;
        public static final String FIND_BY_NAME_CONTAIN = "name/{productName}" ;
        public static final String FILTER_PRODUCT = "filter/{productCategoryIds}/{productBrandIds}" ;
    }
    public static class ProductDetailsURL {
        public static final String PRE_FIX = "product/details" ;
        public static final String FIND_BY_ID ="id/{productDetailsId}" ;
        public static final String FIND_BY_PRODUCT = "productId/{productId}" ;
    }

    public static class ProductImageURL{
        public static final String PRE_FIX = "product/image" ;
        public static final String ADD_PRODUCT_IMAGE ="/{productId}" ;
        public static final String UPD_PRODUCT_IMAGE = "/{productImageId}/{productId}" ;
        public static final String FIND_BY_ID = "id/{request}";
        public static final String FIND_BY_NAME = "name/{request}" ;
        public static final String FIND_BY_PRODUCT = "productId/{request}" ;
    }
}
