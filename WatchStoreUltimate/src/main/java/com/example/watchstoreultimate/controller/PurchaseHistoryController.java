package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.service.ExpertToExcel;
import com.example.watchstoreultimate.service.PurchaseHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.*;

@RestController
@RequestMapping("purchase-history")
@Tag( name = "PURCHASE HISTORY API" ,  description = "REST API PURCHASE HISTORY FOR FRONT-END DEVELOPER")
public class PurchaseHistoryController {
    @Autowired
    PurchaseHistoryService purchaseHistoryService ;
    @Autowired
    ExpertToExcel expertToExcel ;

    @RequestMapping(value = UrlConstant.ROLE_ADMIN , method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "1", required = false) int pageIndex,
                                    @RequestParam(defaultValue = "9", required = false) int pageSize){
        Pageable pageable = PageRequest.of(pageIndex , pageSize) ;
        Response response = purchaseHistoryService.getAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    // Lịch sử mua hàng
    @RequestMapping(value = UrlConstant.PurchaseUrl.GET_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<?> getByCustomer(@PathVariable int customerId){
        Response response = purchaseHistoryService.getHistoryByCustomer(customerId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    // Số lượng bán
    @RequestMapping(value = UrlConstant.PurchaseUrl.GET_BY_PRODUCT , method = RequestMethod.GET)
    public ResponseEntity<?> getByProduct(@PathVariable int productId){
        Response response = purchaseHistoryService.getHistoryByProduct(productId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.PurchaseUrl.GET_TOTAL_SALES, method = RequestMethod.GET)
    public ResponseEntity<?> getTotalSales(){
        Response response = purchaseHistoryService.getTotalSales() ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @RequestMapping(value = UrlConstant.PurchaseUrl.EXCEL, method = RequestMethod.GET)
    public ResponseEntity<?> expertToExcel(@RequestParam(defaultValue = "2023-12-12", required = false) String date1 ,
                                           @RequestParam(defaultValue = "2030-12-12", required = false) String date2 ,
                                           HttpServletResponse servletResponse) throws IOException {
        LocalDate day1 = LocalDate.parse(date1) ;
        LocalDate day2 = LocalDate.parse(date2) ;
        servletResponse.setContentType("application/octet-stream") ;
        String headerKey="Content-Disposition" ;
        String headerValue = "attachment;filename=purchaseHistoryProduct_" + date1 + " To " +date2 + ".xlsx" ;
        servletResponse.setHeader(headerKey , headerValue );
        Response response = expertToExcel.expert(day1, day2, servletResponse) ;
        return  ResponseEntity.ok().body(response.getMessage()) ;
    }


}
