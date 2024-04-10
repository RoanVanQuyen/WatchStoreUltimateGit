package com.example.watchstoreultimate.exception;

import com.example.watchstoreultimate.dto.response.Response;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> appException(AppException appException){
        Response response = Response.builder()
                .code(appException.getErrorCode().getCode())
                .message(appException.getErrorCode().getMessage())
                .build();
        return ResponseEntity.badRequest()
                .body(response) ;
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<?> jwtException(JwtException jwtException){
        return ResponseEntity.badRequest()
                .body(jwtException.getMessage()) ;
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> runtimeException(Exception exception){
        return ResponseEntity.badRequest()
                .body(exception.getMessage()) ;
    }


    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<?> IOException(IOException exception){
        return ResponseEntity.badRequest()
                .body(exception.getMessage()) ;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException validException){
        String message = validException.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.ERR_VALID;
        try {
            errorCode = ErrorCode.valueOf(message);
        }catch (RuntimeException runtimeException){
            System.out.println("Chưa bổ sung lỗi");
        }
        Response response = Response.builder()
                .code(errorCode.getCode())
                .message(message)
                .build();
        return ResponseEntity.badRequest()
                .body(response) ;
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> argumentException(IllegalArgumentException exception){
        ErrorCode errorCode = ErrorCode.ERR_VALID_ARGUMENT ;
        errorCode.setMessage(exception.getMessage());
        Response response = Response.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest()
                .body(response) ;
    }
    //Excaption email
    @ExceptionHandler(value = AuthenticateException.class)
    public ResponseEntity<?> authenticateException(AuthenticateException exception){
        Response response = Response.builder()
                .code(exception.getErrorCode().getCode())
                .message(exception.getErrorCode().getMessage())
                .build() ;
        return ResponseEntity.status(exception.getErrorCode().getCode())
                .body(response) ;
    }
}
