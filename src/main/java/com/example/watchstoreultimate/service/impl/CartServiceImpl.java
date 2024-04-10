package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.CartRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Cart;
import com.example.watchstoreultimate.entity.Customer;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.entity.PurchaseHistory;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.onlinePay.VnpayConfig;
import com.example.watchstoreultimate.repository.CartRepository;
import com.example.watchstoreultimate.repository.CustomerRepository;
import com.example.watchstoreultimate.repository.ProductRepository;
import com.example.watchstoreultimate.repository.PurchaseHistoryRepository;
import com.example.watchstoreultimate.service.CartService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    CustomerRepository customerRepository ;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository ;
    @Override
    public Response addProduct(CartRequest request) {
        Product product = productRepository.findByProductIdAndProductAvailable(request.getProductId(), true).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(),  true).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(cartRepository.existsByCustomerAndProduct(customer , product)){
            throw  new AppException(ErrorCode.CART_EXISTED) ;
        }
        return Response.builder()
                .code(200)
                .result(cartRepository.save(Cart.builder()
                                .product(product)
                                .customer(customer)
                                .cart_add_date(request.getCart_add_date())
                                .cartQuantity(request.getCartQuantity())
                        .build()))
                .message("Add product for cart success")
                .build();
    }

    @Override
    public Response delProduct(List<CartRequest> requests) {
        List<Cart> carts = new ArrayList<>() ;
        for(CartRequest request : requests){
            Product product = productRepository.findByProductIdAndProductAvailable(request.getProductId(), true).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(),  true).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            ) ;
            Optional<Cart> cartOptional = cartRepository.findByCustomerAndProduct(customer , product) ;
            if(cartOptional.isPresent()){
                carts.add(cartOptional.get()) ;
                cartRepository.delete(cartOptional.get());
            }
        }
        return Response.builder()
                .code(200)
                .result(carts)
                .message("Delete success")
                .build() ;
    }

    @Override
    public Response updProduct(CartRequest request) {
        Product product = productRepository.findByProductIdAndProductAvailable(request.getProductId(), true).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        );
        Customer customer = customerRepository.findByCustomerIdAndCustomerAvailable(request.getCustomerId(),  true).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(cartRepository.existsByCustomerAndProduct(customer , product)){
            return Response.builder()
                    .code(200)
                    .result(cartRepository.save(Cart.builder()
                            .product(product)
                            .customer(customer)
                            .cart_add_date(request.getCart_add_date())
                            .cartQuantity(request.getCartQuantity())
                            .build()))
                    .message("Update cart success")
                    .build();
        }
        throw  new AppException(ErrorCode.CART_NOT_EXISTED) ;
    }

    @Override
    public Response getCart(int productId, int customerId) {
        Cart cart = cartRepository.getCartByProductIdAndCustomerId(productId , customerId).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(cart)
                .message("Get cart success")
                .build();
    }

    @Override
    public Response getCartByProduct(int productId) {
        List<Cart> carts = new ArrayList<>() ;
        carts = cartRepository.getCartByProductId(productId) ;
        return Response.builder()
                .code(200)
                .result(carts)
                .message("Get cart by product success")
                .build() ;
    }

    @Override
    public Response getCartByCustomer(int customerId) {
        List<Cart> carts = new ArrayList<>() ;
        carts = cartRepository.getCartByCustomerId(customerId) ;
        return Response.builder()
                .code(200)
                .result(carts)
                .message("Get cart by customer success")
                .build() ;
    }



    private  Map<Integer , List<Cart>> saveInfoCart = new HashMap<>() ;
    @Override
    public Response payCart(List<Cart> requests, HttpServletRequest req) throws UnsupportedEncodingException {
        long sumPrice = 0 ;
        for(Cart x : requests){
            if(!cartRepository.existsByCustomerAndProduct(x.getCustomer() , x.getProduct())){
                throw  new AppException(ErrorCode.CART_NOT_EXISTED) ;
            }
            sumPrice = sumPrice + x.getProduct().getProductPrice() * x.getCartQuantity() ;
        }
        String orderType = "other";
//        String bankCode = req.getParameter("bankCode");

        long amount = sumPrice  * 100;
        String vnp_TxnRef = VnpayConfig.getRandomNumber() + "";
        saveInfoCart.put(Integer.parseInt(vnp_TxnRef) , requests) ;

        String vnp_IpAddr = VnpayConfig.getIpAddress(req);
        String vnp_TmnCode = VnpayConfig.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnpayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VnpayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr" , vnp_IpAddr) ;
        vnp_Params.put("vnp_OrderType", orderType);


        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;
        return Response.builder()
                .code(200)
                .result(paymentUrl)
                .message("Result: URL")
                .build() ;
    }
    @Override
    public Response extractPay(int vnpay_responseCode , int  vnp_TxnRef) {
        Product product = Product.builder().build();
        if(vnpay_responseCode == 00){
            List<Cart> carts = saveInfoCart.get(vnp_TxnRef) ;
            for(Cart cart :carts){
                product = productRepository.findByProductIdAndProductAvailable(cart.getProduct().getProductId(), true).orElseThrow(
                        () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
                ) ;
                product.setProductQuantity(product.getProductQuantity()- cart.getCartQuantity());
                if(product.getProductQuantity() < 1){
                    product.setProductAvailable(false);
                }
                PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                        .product(cart.getProduct())
                        .customer(cart.getCustomer())
                        .quantity(cart.getCartQuantity())
                        .paymentMethod("VN pay")
                        .priceSold(cart.getProduct().getProductPrice() * ((100-cart.getProduct().getProductPriceReduction()) * 100))
                        .build() ;
                purchaseHistoryRepository.save(purchaseHistory) ;
                productRepository.save(product);
                cartRepository.delete(cart);
            }
            return Response.builder()
                    .code(200)
                    .result(carts)
                    .message("Pay success")
                    .build() ;
        }
        throw  new AppException(ErrorCode.PAYMENT_FAILED) ;
    }
}
