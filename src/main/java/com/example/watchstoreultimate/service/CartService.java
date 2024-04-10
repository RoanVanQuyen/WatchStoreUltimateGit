package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.request.CartRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Cart;
import com.example.watchstoreultimate.entity.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.List;

public interface CartService {
    final static boolean CART_AVAILABLE = true ;
    Response addProduct(CartRequest request) ;
    Response delProduct(List<CartRequest> requests) ;
    Response updProduct(CartRequest request) ; // change quantity
    Response getCart(int productId , int customerId) ;
    Response getCartByProduct(int productId) ;
    Response getCartByCustomer(int customerId) ;
    Response extractPay(int vnpay_responseCode ,int vnp_TransactionNo) ;
    Response payCart(List<Cart> requests, HttpServletRequest req) throws UnsupportedEncodingException;
}
