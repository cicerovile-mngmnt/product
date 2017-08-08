package com.fuck.off.fuckoffasaservice;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration()
@ApplicationPath("rest")
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void setUp() {
        register(FuckOffAsAService.class);
    }

}
