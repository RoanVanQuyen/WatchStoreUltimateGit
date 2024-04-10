package com.example.watchstoreultimate.constant;

public class UrlConstant {
    public final static  String FIND = "/search" ;
    public final static String NONE_AUTH = "/abc" ;
    public final static String ROLE_USER = "/1011" ;
    public final static String ROLE_MANAGER = "/1012" ;
    public final static String ROLE_ADMIN = "/1013" ;
    public static class AccountURL{
        public static final String PRE_FIX = "/account" ;
        public static final String SIGN_IN = "/sign-in"+ "/{userName}/{password}" ;
        public static final String REGISTER = "/register" ;
        public static final String U_CHANGE_PASSWORD = ROLE_USER + "/password/{accountId}"  ;
        public static final String FORGET_PASSWORD = "/password/forget/{customerEmail}" ;
        public static final String FORGET_PASSWORD_AUTH = "/password/forget/auth/{customerEmail}/{code}" ;
        public static final String REFRESH_TOKEN = ROLE_USER +  "/token/{refreshToken}" ;
    }

    public static class AddressURL{
        public static final String PRE_FIX = "address" ;
        public static final String U_UPDATE_ADDRESS =  ROLE_USER +"/{addressId}" ;
        public static final String FIND_BY_ID = FIND + "/id/{addressId}" ;
        public static final String FIND_BY_CUSTOMER = FIND + "/customer/{customerId}" ;
    }

    public static class BrandURL{
        public static final String PRE_FIX = "brand";
        public static final String FIND_ALL = FIND ;
        public static final String FIND_BY_ID =  FIND + "/id/{brandId}" ;
        public static final String M_DEL_BRAND = ROLE_MANAGER + "/{brandIds}" ;
        public static final String M_RE_DEL_BRAND = ROLE_MANAGER + "/{brandIds}" ;

    }

    public static class CommentURL{
        public static final String PRE_FIX = "comment" ;
        public static final String U_UPD_COMMENT =  ROLE_USER +"/{commentId}" ;
        public static final String FIND_BY_PRODUCT = FIND +  "/product/{productId}" ;
        public static final String FIND_BY_CUSTOMER =  FIND+ "/customer/{customerId}" ;
        public static final String FIND_BY_COMMENT =  FIND+ "/comment-parent/{commentParentId}" ;
        public static final String U_DEL_COMMENT = ROLE_USER + "/{commentIds}" ;
    }

    public static class CartURL{
        public static final String PRE_FIX = "cart" ;
        public static final String FIND_BY_ID =  FIND + "/{productId}/{customerId}" ;
        public static final String FIND_BY_PRODUCT =  FIND+ "/product/{productId}";
        public static final String FIND_BY_CUSTOMER = FIND+  "/customer/{customerId}" ;
        public static final String U_PAYMENT =  ROLE_USER +"/pay/online" ;
        public static final String U_PAYMENT_RESULT =  ROLE_USER +"/pay/online/result" ;
    }

    public static class CategoryURL{
        public static final String PRE_FIX = "category" ;
        public static final String U_UPD_CATEGORY = ROLE_MANAGER + "/{categoryId}" ;
        public static final String FIND_BY_ID =  FIND+ "/id/{categoryId}" ;
        public static final String FIND_ALL = FIND ;
        public static final String M_DEL_CATEGORY = ROLE_MANAGER + "/{categoryIds}" ;
        public static final String M_RE_DEL_CATEGORY = ROLE_MANAGER + "/retrieve/{categoryIds}" ;
        public static final String M = ROLE_MANAGER + "/category-product" ;
    }


    public  static  class CustomerURL{
        public static final String PRE_FIX = "customer" ;
        public static final String FIND_BY_ID =  FIND+ "/id/{customerId}" ;
        public static final String FIND_ALL =  FIND;
        public static final String M_DEL_CUSTOMER = ROLE_MANAGER + "/{customerIds}" ;
        public static final String A_DEL_CUSTOMER = ROLE_ADMIN + "/{customerIds}" ;
        public static final String SEND_REQUEST_CONFIRM_EMAIL = "email/code/send" ;
        public static final String AUTH_REQUEST_CONFIRM_EMAIL = "email/code/auth/{code}" ;
    }
    public static class ProductURL{
        public static final String PRE_FIX = "product" ;
        public static final String M_UPD_PRODUCT = ROLE_MANAGER + "/{productId}" ;
        public static final String M_DEL_PRODUCT =ROLE_MANAGER + "/{productIds}" ;
        public static final String M_RE_DEL_PRODUCT =ROLE_MANAGER + "/{productIds}" ;
        public static final String GET_FEATURE_PRODUCT = FIND + "/feature" ;
        public static final String GET_NEW_PRODUCT = FIND + "/new" ;
        public static final String GET_SALE_PRODUCT = FIND + "/sale" ;
        public static final String FIND_BY_ID =  FIND+ "/id/{productId}" ;
        public static final String FIND_BY_NAME_CONTAIN =  FIND+ "/name/{productName}" ;
        public static final String FILTER_PRODUCT =  FIND+ "/filter/{productCategoryIds}/{productBrandIds}" ;
    }

    public static class ProductDetailsURL {
        public static final String PRE_FIX = "product/detail" ;
        public static final String FIND_BY_ID = FIND+ "/id/{productDetailsId}" ;
        public static final String FIND_BY_PRODUCT = FIND+  "/productId/{productId}" ;
    }

    public static class ProductImageURL{
        public static final String PRE_FIX = "product/image" ;
        public static final String M_ADD_PRODUCT_IMAGE =ROLE_MANAGER + "/{productId}" ;
        public static final String M_UPD_PRODUCT_IMAGE = ROLE_MANAGER + "/{productImageId}/{productId}" ;
        public static final String FIND_BY_ID = FIND+  "/id/{request}";
        public static final String FIND_BY_PRODUCT =  FIND+ "/productId/{request}" ;
    }
    public static class PurchaseUrl{
        public static final String PRE_FIX = "purchase-history" ;
        public static final String GET_BY_CUSTOMER ="customer/{customerId}" ;
        public static final String GET_BY_PRODUCT = "product/{productId}" ;
        public static final String EXCEL = ROLE_MANAGER + "/excel" ;
        public static final String GET_TOTAL_SALES = ROLE_ADMIN + "sold" ;
    }
    public static class BlogUrl{
        public static final String PRE_FIX = "/blog" ;
        public static final String UPD_BLOG =ROLE_MANAGER +  "/{blogId}" ;
        public static final String DEL_BLOG= ROLE_MANAGER + "/{blogIds}" ;
        public static final String GET_BY_CUSTOMER = FIND +  "/customer/{customerId}" ;
        public static final String GET_BY_ID = FIND + "/id/{blogId}" ;
        public static final String GET_ALL = FIND  ;
    }
}
