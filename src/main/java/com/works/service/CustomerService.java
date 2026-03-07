package com.works.service;

import com.works.entity.Customer;
import com.works.entity.dto.CustomerRegisterDto;
import com.works.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public Customer customerRegister(CustomerRegisterDto customerRegisterDto){
        Optional<Customer> customerOptional = customerRepository.findByEmailEqualsIgnoreCase(customerRegisterDto.getEmail());
        if(customerOptional.isPresent()){
            // throw new RuntimeException("Email already exists");
            throw new RuntimeException("Email already exists");
        }
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
        return customerRepository.save(customer);
    }



}
