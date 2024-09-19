package com.example.watchstoreultimate;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.entity.Product;
import com.example.watchstoreultimate.entity.PurchaseHistory;
import com.example.watchstoreultimate.repository.PurchaseHistoryRepository;
import com.example.watchstoreultimate.service.BrandService;
import com.example.watchstoreultimate.service.ProductService;
import com.example.watchstoreultimate.service.RedisService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.DecimalFormat;
import java.util.*;

@SpringBootApplication
@OpenAPIDefinition(
        info =  @Info(
                title = "Watch store API" ,
                version = "2.0" ,
                description = "RestApi for front-end developer" ,
                contact = @Contact(
                        name = "Roãn Văn Quyền"
                        , email = "presidentquyen@gmail.com"
                )
        ),
        servers =@Server(
                url = "http://localhost:8080",
                description = "Watch store API url"
        )
)
public class WatchStoreUltimateApplication implements CommandLineRunner {
    @Autowired
    private RedisService redisService ;
    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(WatchStoreUltimateApplication.class, args);
    }

    @Autowired
    PurchaseHistoryRepository repository ;
    @Override
    public void run(String... args) throws Exception {
    }
}
