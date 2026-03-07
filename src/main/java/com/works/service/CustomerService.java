package com.works.service;

import com.works.entity.Customer;
import com.works.entity.dto.CustomerRegisterDto;
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

    public Customer customerRegister(CustomerRegisterDto customerRegisterDto){
        Optional<Customer> customerOptional = customerRepository.findByEmailEqualsIgnoreCase(customerRegisterDto.getEmail());
        if(customerOptional.isPresent()){
            // throw new RuntimeException("Email already exists");
            throw new RuntimeException("Email already exists");
        }
        Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
        String newPass = BCrypt.hashpw(customerRegisterDto.getPassword(), BCrypt.gensalt());
        customer.setPassword(newPass);
        return customerRepository.save(customer);
    }


}

// boolean result = BCrypt.checkpw("123456", hash);