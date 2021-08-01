package com.school.academic.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
