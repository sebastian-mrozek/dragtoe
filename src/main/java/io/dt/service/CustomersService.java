package io.dt.service;


import io.dt.api.Customer;
import io.dt.api.NewCustomer;
import io.dt.api.Status;
import io.dt.mapper.CustomerMapper;
import io.dt.service.api.ICustomersService;
import io.dt.service.db.DCustomer;
import io.dt.service.db.query.QDCustomer;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomersService implements ICustomersService {

    CustomerMapper mapper = new CustomerMapper();

    @Override
    final public Customer add(NewCustomer newCustomer) {
        var dCustomer = new DCustomer(
                null,
                newCustomer.getNickName(),
                mapper.apiToDb(newCustomer.getContactDetails()),
                new Date(),
                Status.PROSPECTIVE);
        dCustomer.save();
        return mapper.dbToApi(dCustomer);
    }

    @Override
    final public List<Customer> getAll(String nameFilter, String addressFilter) {
        var customerQuery = new QDCustomer()
                .fetch("contactDetails");

        if (nameFilter != null && !nameFilter.isEmpty()) {
            customerQuery = customerQuery.nickName.contains(nameFilter);
        }

        if (addressFilter != null && !addressFilter.isEmpty()) {
            customerQuery = customerQuery.contactDetails.address.contains(addressFilter);
        }

        return customerQuery
                .findList()
                .stream()
                .map(mapper::dbToApi)
                .collect(Collectors.toList());
    }

    @Override
    final public Customer getById(UUID id) {
        var customer = new QDCustomer().id.eq(id).findOne();
        if (customer == null) {
            return null;
        }
        return mapper.dbToApi(customer);
    }

    @Override
    final public void updateStatus(UUID id, Status status) {
        new QDCustomer()
                .id.eq(id)
                .asUpdate()
                .set("status", status)
                .update();
    }
}
