package com.works.controller;

import com.works.entity.Customer;
import com.works.entity.dto.CustomerLoginDto;
import com.works.entity.dto.CustomerRegisterDto;
import com.works.entity.dto.CustomerResultDto;
import com.works.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("register")
    public CustomerResultDto register(@Valid @RequestBody CustomerRegisterDto customerRegisterDto){
        return customerService.customerRegister(customerRegisterDto);
    }

    @PostMapping("login")
    public CustomerResultDto login(@Valid @RequestBody CustomerLoginDto customerLoginDto){
        return customerService.customerLogin(customerLoginDto);
    }

}