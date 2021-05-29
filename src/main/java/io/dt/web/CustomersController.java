package io.dt.web;

import io.avaje.http.api.Controller;
import io.avaje.http.api.Get;
import io.avaje.http.api.Path;
import io.avaje.http.api.Post;
import io.dt.api.Customer;
import io.dt.api.NewCustomer;

import java.util.ArrayList;
import java.util.List;

@Controller
@Path("customers")
public class CustomersController {

    @Get
    public List<Customer> getAll() {
        return new ArrayList<>();
    }

    @Post
    public Customer add(NewCustomer newCustomer) {
        return null;
    }
}
