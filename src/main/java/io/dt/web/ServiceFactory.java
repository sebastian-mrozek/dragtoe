package io.dt.web;


import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.dt.service.CustomersService;
import io.dt.service.api.ICustomersService;

@Factory
public class ServiceFactory {

    @Bean
    public ICustomersService create() {
        return new CustomersService();
    }
}
