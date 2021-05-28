package io.dt.api.service;

import io.dt.api.Customer;
import io.dt.api.Status;

import java.util.List;
import java.util.UUID;

public interface ICustomersService {

    List<Customer> getAll();

    Customer getById(UUID id);

    void updateStatus(UUID id, Status status);
}
