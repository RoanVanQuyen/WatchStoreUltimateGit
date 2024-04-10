package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Brand;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository@Hidden
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsAllByBrandName(String name);

    Page<Brand> findBrandsByBrandAvailable(Pageable pageable, boolean brandAvailable);

    Optional<Brand> findBrandByBrandIdAndBrandAvailable(int brandId, boolean brandAvailable);
}
