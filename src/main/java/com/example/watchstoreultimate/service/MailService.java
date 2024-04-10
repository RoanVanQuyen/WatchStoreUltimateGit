package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.response.Response;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {
    String sendMail(String email) throws MessagingException, UnsupportedEncodingException;
    Response authCode(String email , int code) ;
}
