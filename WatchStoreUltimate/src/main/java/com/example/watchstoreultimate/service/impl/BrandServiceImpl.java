package com.example.watchstoreultimate.service.impl;

import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.exception.AppException;
import com.example.watchstoreultimate.exception.ErrorCode;
import com.example.watchstoreultimate.mapper.BrandMapper;
import com.example.watchstoreultimate.repository.BrandRepository;
import com.example.watchstoreultimate.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository ;
    @Autowired
    private BrandMapper brandMapper ;

    @Override
    public Response addBrand(BrandRequest request) {
        Brand brand = brandMapper.toBrand(request) ;
        if(brandRepository.existsAllByBrandName(brand.getBrandName())){
            throw new AppException(ErrorCode.NAME_EXISTED) ;
        }
        brandRepository.save(brand) ;
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
        return Response.builder()
                .code(200)
                .result(brandRepository.save(request))
                .message("Success")
                .build();
    }

    @Override
    public Response findAll(Pageable pageable) {
        List<Brand> brands = new ArrayList<>() ;
        brands = brandRepository.findBrandsByBrandAvailable(pageable , BRAND_AVAILABLE).getContent() ;
        Response response = new Response() ;
        return response.builder()
                .code(200)
                .result(brands)
                .message("Success")
                .build();
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
        return Response.builder()
                .code(200)
                .result(brands)
                .message("Success")
                .build();
    }

    @Override
    public Response findBrand(int request_id) {
        Brand brandOptional = brandRepository.findBrandByBrandIdAndBrandAvailable(request_id, BRAND_AVAILABLE).orElseThrow(
                ()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND)
        ) ;
        return Response.builder()
                .code(200)
                .result(brandOptional)
                .message("Success")
                .build();
    }

}
