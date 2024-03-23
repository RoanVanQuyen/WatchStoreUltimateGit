package com.example.watchstoreultimate.exception;

import com.example.watchstoreultimate.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloblaException {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> appException(AppException appException){
        Response response = Response.builder()
                .code(appException.getErrorCode().getCode())
                .message(appException.getErrorCode().getMessage())
                .build();
        return ResponseEntity.badRequest()
                .body(response) ;
    }


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException runtimeException){
        return ResponseEntity.badRequest()
                .body(runtimeException.getMessage()) ;
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
}
