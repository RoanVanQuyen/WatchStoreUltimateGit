package com.example.watchstoreultimate.exception;

import com.example.watchstoreultimate.constant.ErrorValid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    NAME_EXISTED(111 , "Tên đã được sử dụng")
    , CART_EXISTED(10 ,"Giỏ hàng đã tồn tại")
    , CART_NOT_EXISTED(10 ,"Giỏ hàng không đã tồn tại")
    , PAYMENT_FAILED(400 , "Thanh toán thất bại" )
    , USERNAME_EXISTED(1111 , "Tên đăng nhập đã được sử dụng")
    , USERNAME_NOT_FOUNT(111101 , "Không tồn tại tên đăng nhập")
    ,EMAIL_EXISTED(109 , "Email đã tồn tại")
    ,EMAIL_NO_CHANGE(999 , "Không được thay đổi email")
    ,PHONE_EXISTED(108 , "Số điện thoại đã được sử dụng")
    , ERR_ID_NOT_FOUND(404 ,"Không tìm thấy thông qua ID")
    , ERR_EMAIL_CODE_NOT_ACC(404 , "Sai mã xác thực")
    , ERR_VALID(101 , "Chưa bổ sung")
    , ERR_VALID_ARGUMENT(90 , "")
    , ERR_BRAND_NAME_VALID(100 , ErrorValid.ERR_BRAND_NAME_VALID)
    , ERR_BRAND_DETAILS_VALID(99 , ErrorValid.ERR_BRAND_DETAILS_VALID)
    , ERR_CUSTOMER_EMAIL_VALID(112, ErrorValid.ERR_CUSTOMER_EMAIL_VALID)
    , ERR_CUSTOMER_PHONE_VALID(113 , ErrorValid.ERR_CUSTOMER_PHONE_VALID)
    , ERR_ADDRESS_EXISTED_FOR_CUSTOMER(9898 , "Khách hàng đã tồn tại địa chỉ");
    ;
    int code ;
    String message ;

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
