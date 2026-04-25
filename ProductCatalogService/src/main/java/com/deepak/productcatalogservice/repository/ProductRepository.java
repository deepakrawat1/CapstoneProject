package com.deepak.productcatalogservice.repository;

import com.deepak.productcatalogservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryId(Long id, Pageable pageable);

    @Query("Select p from Product p where p.name like concat('%',:keyword,'%')")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
}
