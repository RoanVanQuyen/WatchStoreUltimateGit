package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.dto.response.PageCustom;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.BrandMapper;
import com.example.watchstoreultimate.repository.BrandRepository;
import com.example.watchstoreultimate.service.BrandService;
import com.example.watchstoreultimate.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository ;
    @Autowired
    private BrandMapper brandMapper ;
    @Autowired
    RedisService redisService ;
    final static String KEY = "brand" ;

    @Override
    public Response addBrand(BrandRequest request) {
        Brand brand = brandMapper.toBrand(request) ;
        if(brandRepository.existsAllByBrandName(brand.getBrandName())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        brandRepository.save(brand) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(brand)
                .message("Success")
                .build();
    }

    @Override
    public Response updBrand(Brand request) {
        brandRepository.findBrandByBrandIdAndBrandAvailable(request.getBrandId() , false).orElseThrow(
                () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        if(brandRepository.existsAllByBrandName(request.getBrandName())){
            throw new AppException(ErrorCode.NAME_EXISTED);
        }
        brandRepository.save(request) ;
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(request)
                .message("Success")
                .build();
    }

    @Override
    public Response findAll(Pageable pageable) {
        String field = pageable.toString() ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null ) {
            Page<Brand> page = brandRepository.findBrandsByBrandAvailable(pageable, BRAND_AVAILABLE);
            PageCustom<Brand> pageCustom = PageCustom.<Brand>builder()
                    .pageIndex(pageable.getPageNumber())
                    .pageElement(pageable.getPageSize())
                    .pageSize(page.getTotalPages())
                    .content(page.getContent())
                    .sort(pageable.getSort().toString())
                    .build();
            response =  Response.builder()
                    .code(200)
                    .result(pageCustom)
                    .message("Success")
                    .build();
            redisService.hashSet(KEY, field , response);
        }
        return response ;
    }

    @Override
    public Response delBrand(List<Integer> request_ids) {
        List<Brand> brands = new ArrayList<>() ;
        for(Integer x : request_ids){
            Optional<Brand> brandOptional = brandRepository.findById(x);
            if(brandOptional.isPresent()){
                Brand brand = brandOptional.get() ;
                brand.setBrandAvailable(BRAND_AVAILABLE_NOT);
                brandRepository.save(brand) ;
                brands.add(brand) ;
            }
        }
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(brands)
                .message("Success")
                .build();
    }

    @Override
    public Response reDelBrand(List<Integer> request_ids) {
        List<Brand> brands = new ArrayList<>() ;
        for(Integer x : request_ids){
            Optional<Brand> brandOptional = brandRepository.findById(x);
            if(brandOptional.isPresent()){
                Brand brand = brandOptional.get() ;
                brand.setBrandAvailable(BRAND_AVAILABLE);
                brandRepository.save(brand) ;
                brands.add(brand) ;
            }
        }
        redisService.hashDel(KEY);
        return Response.builder()
                .code(200)
                .result(brands)
                .message("Success")
                .build();
    }

    @Override
    public Response findBrand(int request_id) {
        String field = "brandId: " + request_id ;
        Response response = (Response) redisService.hashGet(KEY , field);
        if(response == null) {
            Brand brandOptional = brandRepository.findBrandByBrandIdAndBrandAvailable(request_id, BRAND_AVAILABLE).orElseThrow(
                    () -> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
            );
            response = Response.builder()
                    .code(200)
                    .result(brandOptional)
                    .message("Success")
                    .build();
            redisService.hashSet(KEY,  field , response);
        }
        return response ;
    }

}
