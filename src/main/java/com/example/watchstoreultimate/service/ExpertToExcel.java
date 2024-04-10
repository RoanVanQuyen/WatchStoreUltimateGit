package com.example.watchstoreultimate.service;

import com.example.watchstoreultimate.dto.response.Response;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public interface ExpertToExcel {
    Response expert(LocalDate date1 , LocalDate date2 , HttpServletResponse response)throws IOException;
}
