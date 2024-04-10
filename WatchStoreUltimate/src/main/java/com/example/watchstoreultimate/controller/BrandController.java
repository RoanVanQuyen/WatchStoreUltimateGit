package com.example.watchstoreultimate.controller;

import com.example.watchstoreultimate.constant.UrlConstant;
import com.example.watchstoreultimate.dto.request.BrandRequest;
import com.example.watchstoreultimate.dto.response.Response;
import com.example.watchstoreultimate.entity.Brand;
import com.example.watchstoreultimate.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.BrandURL.PRE_FIX)
@Tag(name = "Brand API" , description = "RestApi of Brand")
public class BrandController {
    @Autowired
    BrandService brandService ;

    @RequestMapping(value = UrlConstant.ROLE_MANAGER  ,method = RequestMethod.POST)
    @Operation(
            summary = "POST BrandRequest"
            ,description = "ADD Brand"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content) ,
            @ApiResponse(responseCode = "111", description = "Brand name is existed", content = @Content)
    })
    public ResponseEntity<?> addBrand(@RequestBody @Valid BrandRequest request){
        Response response = brandService.addBrand(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @Operation(
            summary = "PUT BrandRequest"
            ,description = "UPDATE BRAND"
    )
    @RequestMapping(value = UrlConstant.ROLE_MANAGER ,method = RequestMethod.PUT)
    public ResponseEntity<?> updBrand(@RequestBody @Valid Brand request){
        Response response = brandService.updBrand(request) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }

    @Operation(
            summary = "GET BrandRequest"
            ,description = "GET All BRAND"
    )
    @RequestMapping(value = UrlConstant.BrandURL.FIND_ALL, method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "1" , required = false) int pageIndex ,
                                     @RequestParam(defaultValue = "9" , required = false) int pageElement) throws Exception{
        Pageable pageable = PageRequest.of(pageIndex - 1, pageElement);
        Response response = brandService.findAll(pageable) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @Operation(
            summary = "GET BrandRequest"
            ,description = "GET All BRAND BY ID"
    )
    @RequestMapping(value = UrlConstant.BrandURL.FIND_BY_ID ,  method = RequestMethod.GET)
    public ResponseEntity<?> findBrandById(@PathVariable int brandId){
        Response response = brandService.findBrand(brandId) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @Operation(
            summary = "DELETE BrandRequest"
            ,description = "DELETE BRAND BY ID"
    )
    @RequestMapping(value = UrlConstant.BrandURL.M_DEL_BRAND, method = RequestMethod.DELETE)
    public ResponseEntity<?> delBrand(@PathVariable List<Integer> brandIds){
        Response response = brandService.delBrand(brandIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }
    @Operation(
            summary = "PUT BrandRequest"
            ,description = "RESTORE DELETE BRAND BY ID"
    )
    @RequestMapping(value = UrlConstant.BrandURL.M_RE_DEL_BRAND, method = RequestMethod.PUT)
    public ResponseEntity<?> reDelBrand(@PathVariable List<Integer> brandIds){
        Response response = brandService.reDelBrand(brandIds) ;
        return ResponseEntity.status(response.getCode())
                .body(response) ;
    }


}
