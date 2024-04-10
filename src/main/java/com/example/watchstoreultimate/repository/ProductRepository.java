package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Product;
import io.swagger.v3.oas.annotations.Hidden;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@Hidden
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductIdAndProductAvailable(int productId , boolean Available) ;
    Page<Product> findByProductNameContainsIgnoreCaseAndProductAvailable(String name , Pageable pageable, boolean Available) ;
    boolean existsByProductName(String name) ;
    @Query(value = "SELECT  p " +
            "FROM Product p " +
            "LEFT JOIN CategoryProduct c on c.product.productId = p.productId " +
            "WHERE ( p.brand.brandId IN :productBrandIds " +
            "OR " +
            "c.category.categoryId IN :productCategoryIds) " +
            "AND " +
            "(p.productPrice >= :minPrice AND p.productPrice <= :maxPrice AND p.productAvailable = :available ) ")
    Page<Product> filterProduct(List<Integer> productCategoryIds , List<Integer> productBrandIds
            , int minPrice , int maxPrice , Pageable pageable, boolean available) ;


    @Query(value = "FROM Product p " +
            "INNER  JOIN PurchaseHistory  ph on p.productId = ph.product.productId " +
            "WHERE p.productAvailable = true " +
            "GROUP BY p.productId " +
            "order by count(*) desc " +
            "LIMIT 10")
    List<Product> getFeatureProduct() ;

    @Query(value = "FROM Product  p " +
            "WHERE p.productAvailable = true " +
            "ORDER BY p.productSaleDate desc " +
            "LIMIT 10")
    List<Product> getNewProduct() ;

    @Query(value = "FROM Product  p " +
            "WHERE  p.productAvailable= true " +
            "ORDER BY p.productPriceReduction desc ")
    List<Product> getSaleProduct() ;

}
