package com.example.watchstoreultimate.constant;

public class ErrorResponseMessage {
    public final static String ERR_ID_NOT_FOUND = "Không tìm thấy đối tượng bằng mã định danh" ;
    public final static String NAME_EXISTED = "Tên đã tồn tại" ;


    public static class Account{
        public final static String USERNAME_EXISTED = "Tên đăng nhập đã tồn tại" ;
        public final static String USERNAME_NOT_FOUND = "Tên đăng nhập không tồn tại" ;
        public final static String ERR_TOKEN = "Token không hợp lệ" ;
    }
    public static class Address{
        public final static String ERR_ADDRESS_EXISTED_FOR_CUSTOMER = "Khách hàng đã tồn taị địa chỉ" ;
    }

    public static class Product{

    }
    public static class Category{
        public final static String NAME_EXISTED = "Tên thẻ loại đã tồn tại" ;
    }
    public static class Customer{
        public final static String ERR_CUSTOMER_EMAIL_VALID = "Định dạng email chưa đúng" ;
        public final static String ERR_CUSTOMER_PHONE_VALID = "Số điện thoại chỉ gồm 10 số"  ;
        public final static String EMAIL_EXISTED = "Email đã tồn tại" ;
        public final static String PHONE_EXISTED =  "Số điện thoại đã tồn tại" ;
        public final static  String EMAIL_NO_CHANGE = "Không thể thay đổi Email" ;
        public final static String ERR_AUTH_EMAIL = "Lỗi xác thực email" ;
    }
    public static class Cart{
        public final static String ERR_CART_EXISTED = "Giỏ hàng đã tồn tại không thể thêm mới" ;
        public final static String ERR_CART_NOT_EXISTED =  "Không tồn tại sản phẩm trong giỏ hàng";
        public final static String PAYMENT_FAILED = "Thanh toán thất bại" ;
    }
}
