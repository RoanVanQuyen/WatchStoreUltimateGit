package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class  MailServiceImpl implements MailService {
    private Map<String , Integer> saveCode = new HashMap<>() ;
    @Autowired
    JavaMailSender javaMailSender ;
    private int getCode(){
        Random random = new Random() ;
        return random.nextInt(100000,999999);
    }
    @Override
    public String sendMail(String email) throws MessagingException, UnsupportedEncodingException {
        int code = getCode() ;
        MimeMessage message = javaMailSender.createMimeMessage() ;
        MimeMessageHelper helper = new MimeMessageHelper(message) ;
        helper.setFrom("cuquyen2412@gmail.com" , "Watch Store Ultimate");
        helper.setTo(email);
        helper.setSubject("Mã xác thực tài khoản - WATCH STORE ULTIMATE");
        helper.setText("<p style = 'font-size: 24px' >WSU- " + code + " là  mã xác thực tài khoản của bạn, vui lòng không cung cấp cho người lạ " + "</p>" , true);
        helper.setSentDate(new Date(System.currentTimeMillis()));
        javaMailSender.send(message); //EMAIL- 5852151 là mã xác thực tài khoản của bạn , vui lòng không cung cấp nó cho ai
        saveCode.put(email , code) ;
        return "Send email success";
    }

    @Override
    public Response authCode(String email, int code) {
        int a = saveCode.get(email);
        if(a == code){
            return Response.builder()
                    .code(200)
                    .message("Auth code success")
                    .build() ;
        }
        else{
            throw new AppException(ErrorCode.ERR_EMAIL_CODE_NOT_ACC) ;
        }
    }
}
