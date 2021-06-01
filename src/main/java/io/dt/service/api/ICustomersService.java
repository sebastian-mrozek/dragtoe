package io.dt.service.api;

import io.dt.api.Customer;
import io.dt.api.NewCustomer;
import io.dt.api.Status;

import java.util.List;
import java.util.UUID;

public interface ICustomersService {

    Customer add(NewCustomer newCustomer);

    default List<Customer> getAll() {
        return getAll(null, null);
    };

    List<Customer> getAll(String nameFilter, String addressFilter);

    Customer getById(UUID id);

    void updateStatus(UUID id, Status status);
}
