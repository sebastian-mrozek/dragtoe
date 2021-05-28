package io.dt.service;


import io.dt.api.Customer;
import io.dt.api.Status;
import io.dt.api.service.ICustomersService;

import java.util.List;
import java.util.UUID;

public class CustomersService implements ICustomersService {
    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(UUID id) {
        return null;
    }

    @Override
    public void updateStatus(UUID id, Status status) {

    }
}
