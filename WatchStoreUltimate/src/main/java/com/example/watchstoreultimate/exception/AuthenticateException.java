package com.example.watchstoreultimate.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class AuthenticateException extends Exception{
    private ErrorCode errorCode ;
}
