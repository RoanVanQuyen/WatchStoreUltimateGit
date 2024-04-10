package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.CartRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Cart;
import com.example.watchstoreultimate.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.CartURL.PRE_FIX)
@Tag( name = "CART API",  description = "REST API ADDRESS FOR FRONT-END DEVELOPER" )
public class CartController {
    @Autowired
    private CartService cartService ;

    @RequestMapping(value = UrlConstant.ROLE_USER ,method = RequestMethod.POST)
    public ResponseEntity<?> addCart(@RequestBody @Valid CartRequest request){
        Response response = cartService.addProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @RequestMapping(value = UrlConstant.ROLE_USER , method = RequestMethod.PUT)
    public ResponseEntity<?> updCart(@RequestBody @Valid CartRequest request){
        Response response = cartService.updProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.ROLE_USER,method = RequestMethod.DELETE)
    public ResponseEntity<?> delCart(@RequestBody @Valid List<CartRequest> request){
        Response response = cartService.delProduct(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CartURL.FIND_BY_ID,method = RequestMethod.GET)
    public ResponseEntity<?> getCart(@PathVariable int productId ,
                                     @PathVariable int customerId){
        Response response = cartService.getCart(productId,customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CartURL.FIND_BY_PRODUCT,method = RequestMethod.GET)
    public ResponseEntity<?> getCartByProduct(@PathVariable int productId ){
        Response response = cartService.getCartByProduct(productId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CartURL.FIND_BY_CUSTOMER,method = RequestMethod.GET)
    public ResponseEntity<?> getCartByCustomer(@PathVariable int customerId ){
        Response response = cartService.getCartByCustomer(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.CartURL.U_PAYMENT, method = RequestMethod.POST)
    public ResponseEntity<?> payCart(@RequestBody @Valid List<Cart> request , HttpServletRequest req) throws UnsupportedEncodingException {
        Response response = cartService.payCart(request , req) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
//    http://localhost:8080/ket-qua-thanh-toan?vnp_Amount=6290000000&vnp_BankCode=NCB&vnp_BankTranNo=VNP14352361&vnp_CardType=ATM&vnp_OrderInfo=Thanh+toan+don+hang%3A32641187&vnp_PayDate=20240323003738&vnp_ResponseCode=00&vnp_TmnCode=4OJH0IKW&vnp_TransactionNo=14352361&vnp_TransactionStatus=00&vnp_TxnRef=32641187&vnp_SecureHash=739b75fe1a884d530be13041b11120d3d2558a074af5325e20e74b0ced08fd16ad52af29a2b6a49300abfd44ddfb8a105d97a3a13e3ead8591064059eaf3bc6a
    @RequestMapping(value = UrlConstant.CartURL.U_PAYMENT_RESULT, method = RequestMethod.GET)
    public ResponseEntity<?> resultPay(@RequestParam int vnp_ResponseCode,
                                       @RequestParam int vnp_TxnRef){
        Response response = cartService.extractPay(vnp_ResponseCode ,vnp_TxnRef) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
}
