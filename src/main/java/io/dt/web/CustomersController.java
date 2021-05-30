package io.dt.web;

import io.avaje.http.api.Controller;
import io.avaje.http.api.Get;
import io.avaje.http.api.Path;
import io.avaje.http.api.Post;
import io.dt.api.Customer;
import io.dt.api.NewCustomer;
import io.dt.service.api.ICustomersService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@Controller
@Path("customers")
public class CustomersController {

    private final ICustomersService customersService;

    @Inject
    public CustomersController(ICustomersService customersService) {
        this.customersService = customersService;
    }

    @Get
    public List<Customer> getAll() {
        return customersService.getAll();
    }

    @Post
    public Customer add(NewCustomer newCustomer) {
        return customersService.add(newCustomer);
    }

    @Get("{id}")
    public Customer getById(UUID id) {
        return customersService.getById(id);
    }
}
