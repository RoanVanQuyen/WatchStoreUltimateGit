package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.BrandURL.PRE_FIX)
public class BrandController {
    @Autowired
    BrandService brandService ;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBrand(@RequestBody @Valid BrandRequest request){
        Response response = brandService.addBrand(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updBrand(@RequestBody @Valid Brand request){
        Response response = brandService.updBrand(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.BrandURL.FIND_ALL, method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                     @RequestParam(defaultValue = "9" , required = false) int pageElement) throws Exception{
        Pageable pageable = PageRequest.of(pageIndex - 1, pageElement);
        Response response = brandService.findAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.BrandURL.FIND_BY_ID ,  method = RequestMethod.GET)
    public ResponseEntity<?> findBrandById(@PathVariable int brandId){
        Response response = brandService.findBrand(brandId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.BrandURL.DEL_BRAND , method = RequestMethod.DELETE)
    public ResponseEntity<?> delBrand(@PathVariable List<Integer> brandIds){
        Response response = brandService.delBrand(brandIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @RequestMapping(value = UrlConstant.BrandURL.RE_DEL_BRAND, method = RequestMethod.PUT)
    public ResponseEntity<?> reDelBrand(@PathVariable List<Integer> brandIds){
        Response response = brandService.reDelBrand(brandIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


}
