package com.works.config;

import com.works.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {

    @Bean(name = "modelMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "configModelMapper")
    public ModelMapper modelMapperConfig(){
        ModelMapper mp1 = new ModelMapper();
        mp1.getConfiguration().setFieldMatchingEnabled(true);
        return mp1;
    }

}
