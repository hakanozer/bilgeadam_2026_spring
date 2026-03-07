package com.works.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entity.Customer}
 */
@Value
public class CustomerRegisterDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 100)
    @NotEmpty
    String name;
    @NotNull
    @Size(min = 2, max = 100)
    @NotEmpty
    String surname;
    @NotNull
    @Email
    @NotEmpty
    String email;
    @NotNull
    @Size(min = 5, max = 15)
    @NotEmpty
    String password;
}