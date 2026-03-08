package com.works.entity;

import com.works.utils.ECategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ECategory category;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
