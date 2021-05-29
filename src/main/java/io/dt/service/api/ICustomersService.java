package io.dt.service.api;

import io.dt.api.ContactDetails;
import io.dt.api.Customer;
import io.dt.api.Status;

import java.util.List;
import java.util.UUID;

public interface ICustomersService {

    Customer add(String nickName, ContactDetails contactDetails);

    List<Customer> getAll();

    Customer getById(UUID id);

    void updateStatus(UUID id, Status status);
}
