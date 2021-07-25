package com.school.studentmanagementservice.config;

import com.school.studentmanagementservice.service.UserMappingService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Data
@Configuration
public class ApplicationConfig {
    private final UserMappingService mappingService;

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @PostConstruct
    void startUp(){
        mappingService.addAdmin();
    }
}
