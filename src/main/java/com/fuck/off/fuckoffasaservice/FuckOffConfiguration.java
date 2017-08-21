package com.fuck.off.fuckoffasaservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuckOffConfiguration {

    @Bean
    public FuckOffScheduler scheduler() {
        return new FuckOffScheduler();
    }

}