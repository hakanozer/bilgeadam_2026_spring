package com.works.entity.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entity.Customer}
 */
@Data
public class CustomerResultDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String email;
}