package com.example.ZipcodeAPI;

import com.example.ZipcodeAPI.service.ZipcodeService;
import com.example.ZipcodeAPI.steps.ZipCodeApiSteps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ZipCodeApiSteps zipCodeApiSteps(){
        return new ZipCodeApiSteps();
    }

    @Bean
    public ZipcodeService zipcodeService(){
        return new ZipcodeService();
    }
}
