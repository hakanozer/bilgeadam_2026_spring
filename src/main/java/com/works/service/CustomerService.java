package com.works.service;

import com.works.entity.Customer;
import com.works.entity.dto.CustomerLoginDto;
import com.works.entity.dto.CustomerRegisterDto;
import com.works.entity.dto.CustomerResultDto;
import com.works.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;

    public CustomerResultDto customerRegister(CustomerRegisterDto customerRegisterDto){
        Optional<Customer> customerOptional = customerRepository.findByEmailEqualsIgnoreCase(customerRegisterDto.getEmail());
        if(customerOptional.isPresent()){
            // throw new RuntimeException("Email already exists");
            throw new RuntimeException("Email already exists");
        }
        Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
        String newPass = BCrypt.hashpw(customerRegisterDto.getPassword(), BCrypt.gensalt());
        customer.setPassword(newPass);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResultDto customerResultDto = modelMapper.map(savedCustomer, CustomerResultDto.class);
        return customerResultDto;
    }

    public CustomerResultDto customerLogin(CustomerLoginDto customerLoginDto){
        Optional<Customer> customerOptional = customerRepository.findByEmailEqualsIgnoreCase(customerLoginDto.getEmail());
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            boolean result = BCrypt.checkpw(customerLoginDto.getPassword(), customer.getPassword());
            if(result){
                CustomerResultDto customerResultDto = modelMapper.map(customer, CustomerResultDto.class);
                return customerResultDto;
            }
        }
        throw new RuntimeException("Email or Password is wrong");
    }


}

// boolean result = BCrypt.checkpw("123456", hash);