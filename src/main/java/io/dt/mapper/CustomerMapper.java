package io.dt.mapper;

import io.dt.api.ContactDetails;
import io.dt.api.Customer;
import io.dt.service.db.DContactDetails;
import io.dt.service.db.DCustomer;

public class CustomerMapper {

    public Customer dbToApi(DCustomer dCustomer) {
        ContactDetails contactDetails = dbToApi(dCustomer.contactDetails);
        return new Customer(
                dCustomer.id,
                dCustomer.nickName,
                contactDetails,
                dCustomer.creationDateTime,
                dCustomer.status);
    }

    public ContactDetails dbToApi(DContactDetails dContactDetails) {
        return new ContactDetails(
                dContactDetails.address,
                dContactDetails.phoneNumber,
                dContactDetails.twitterHandle
        );
    }

    public DCustomer apiToDb(Customer customer) {
        DContactDetails dContactDetails = apiToDb(customer.getContactDetails());
        return new DCustomer(
                customer.getId(),
                customer.getNickName(),
                dContactDetails,
                customer.getCreationDateTime(),
                customer.getStatus()
        );
    }

    public DContactDetails apiToDb(ContactDetails contactDetails) {
        return new DContactDetails(
                contactDetails.getAddress(),
                contactDetails.getPhoneNumber(),
                contactDetails.getTwitterHandle()
        );
    }
}
