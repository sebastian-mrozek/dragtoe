package io.dt.service;

import io.dt.service.db.query.QDCustomer;

public class CustomersServiceWithDelete extends CustomersService {

    public void deleteAll() {
        new QDCustomer().delete();
    }
}
