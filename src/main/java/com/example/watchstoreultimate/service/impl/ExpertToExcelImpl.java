package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Address;
import com.example.watchstoreultimate.entity.PurchaseHistory;
import com.example.watchstoreultimate.repository.PurchaseHistoryRepository;
import com.example.watchstoreultimate.service.ExpertToExcel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
@Service
public class ExpertToExcelImpl implements ExpertToExcel {
    @Autowired
    PurchaseHistoryRepository repository ;
    XSSFWorkbook workbook = new XSSFWorkbook() ;
    XSSFSheet sheet = workbook.createSheet("Purchase History Product") ;

    private void setCell(Cell cell , String value , boolean bold){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont() ;
        font.setBold(bold);
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 20);
        font.setFontHeightInPoints((short)18);
        style.setFont(font);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private String formatPrice(int price){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###") ;
        return decimalFormat.format(price);
    }
    @Override
    public Response expert(LocalDate date1, LocalDate date2 , HttpServletResponse response) throws IOException{
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(1 ,5500);
        sheet.setColumnWidth(2 ,5000);
        sheet.setColumnWidth(3 ,18000);
        sheet.setColumnWidth(4 ,8500);
        sheet.setColumnWidth(5 ,4000);
        sheet.setColumnWidth(6 ,4500);
        sheet.setColumnWidth(7 ,5500);
        sheet.setColumnWidth(8 ,5500);
        sheet.setColumnWidth(9 ,5500);
        List<PurchaseHistory> purchaseHistories = repository.findAll() ;
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 600);
        setCell(row.createCell(0), "Số thứ tự" , true );
        setCell(row.createCell(1), "Tên người dùng" ,true  );
        setCell(row.createCell(2), "Số điện thoại" , true );
        setCell(row.createCell(3), "Địa chỉ nhận hàng" , true );
        setCell(row.createCell(4), "Tên sản phẩm" , true );
        setCell(row.createCell(5), "Giá sản phẩm" , true );
        setCell(row.createCell(6), "Số lượng mua" ,true  );
        setCell(row.createCell(7), "Tổng tiền" ,true  );
        setCell(row.createCell(8), "Phương thức thanh toán" , true );
        setCell(row.createCell(9), "Ngày mua" ,true );
        int rowIndex = 1 ;
        for(PurchaseHistory x : purchaseHistories){
            row = sheet.createRow(rowIndex);
            row.setHeight((short) 550);
            setCell(row.createCell(0), rowIndex++ + "", false );
            setCell(row.createCell(1), x.getCustomer().getCustomerName(),false  );
            setCell(row.createCell(2), x.getCustomer().getCustomerPhone(), false );
            Address address = x.getCustomer().getAddress();
            setCell(row.createCell(3), address.getAddressDetails() + "," + address.getAddressCommune() + "," + address.getAddressDistrict() + "," + address.getAddressProvince() , false );
            setCell(row.createCell(4), x.getProduct().getProductName() , false );
            setCell(row.createCell(5), formatPrice(x.getPriceSold()), false );
            setCell(row.createCell(6), x.getQuantity() + "" ,false  );
            setCell(row.createCell(7), formatPrice(x.getQuantity() * x.getPriceSold()) , false );
            setCell(row.createCell(8), x.getPaymentMethod() , false );
            setCell(row.createCell(9), x.getPurchaseHistoryDay() + "" ,false );
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return Response.builder()
                .code(200)
                .message("Success")
                .build();
    }
}
