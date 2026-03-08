package com.works.repository;

import com.works.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where id = ?1", nativeQuery = true)
    List<Product> customQuery(Long id);

    Page<Product> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String title, String description, Pageable pageable);

}