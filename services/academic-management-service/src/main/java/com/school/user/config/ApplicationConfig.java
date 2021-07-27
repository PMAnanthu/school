package com.school.user.config;

import com.school.user.service.UserMappingService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationConfig {
    private final UserMappingService mappingService;

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
