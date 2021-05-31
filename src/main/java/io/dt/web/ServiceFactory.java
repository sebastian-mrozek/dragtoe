package io.dt.web;


import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.dt.service.CustomersService;
import io.dt.service.NotesService;
import io.dt.service.api.ICustomersService;
import io.dt.service.api.INotesService;

@Factory
public class ServiceFactory {

    @Bean
    public ICustomersService createCustomersService() {
        return new CustomersService();
    }
    @Bean
    public INotesService createNotesService() {
        return new NotesService();
    }
}
