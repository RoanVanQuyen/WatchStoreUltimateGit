package com.example.watchstoreultimate.service;


import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BrandService{
    static final boolean BRAND_AVAILABLE_NOT = false ;
    static final boolean BRAND_AVAILABLE = true ;
    Response addBrand(BrandRequest request) ;
    Response updBrand(Brand request) ;
    Response delBrand(List<Integer> request_ids) ;
    Response reDelBrand(List<Integer> request_ids) ;
    Response findBrand(int request_id) ;
    Response findAll(Pageable pageable) ;

}
