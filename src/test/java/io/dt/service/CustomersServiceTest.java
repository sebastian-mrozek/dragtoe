package io.dt.service;

import io.dt.api.ContactDetails;
import io.dt.api.Customer;
import io.dt.api.NewCustomer;
import io.dt.api.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomersServiceTest {

    public static final String TWITTER_HANDLE = "realryannz";
    public static final String PHONE_NUMBER = "0123345566";
    public static final String ADDRESS = "Proper St. 1, Auckland, NZ";

    CustomersServiceWithDelete service = new CustomersServiceWithDelete();

    @BeforeEach
    public void reset() {
        service.deleteAll();
    }

    @Test
    public void testAdd() {
        Customer customer = service.add(new NewCustomer("Ryan", mockContactDetails()));
        assertThat(customer).describedAs("Expected non-null reference return when adding new customer").isNotNull();
        assertThat(customer.getId()).describedAs("id").isNotNull();
        assertThat(customer.getNickName()).describedAs("nick name").isEqualTo("Ryan");
        assertThat(customer.getContactDetails().getAddress()).describedAs("address").isEqualTo(ADDRESS);
        assertThat(customer.getContactDetails().getTwitterHandle()).describedAs("twitter handle").isEqualTo(TWITTER_HANDLE);
        assertThat(customer.getContactDetails().getPhoneNumber()).describedAs("phone number").isEqualTo(PHONE_NUMBER);
        assertThat(customer.getStatus()).describedAs("status").isEqualTo(Status.PROSPECTIVE);
    }

    @Test
    public void testGetAllNoCustomers() {
        List<Customer> initialList = service.getAll();
        assertThat(initialList).describedAs("Expected empty list before adding any customers").isEmpty();
    }

    @Test
    public void testGetAll() {
        Customer andy = service.add(new NewCustomer("Andy", mockContactDetails()));
        Customer tom = service.add(new NewCustomer("Tom", mockContactDetails()));

        List<Customer> actual = service.getAll();

        assertThat(actual).describedAs("customers list").isNotNull();
        assertThat(actual.size()).describedAs("customers count").isEqualTo(2);
        assertThat(actual).containsExactlyInAnyOrder(tom, andy);
    }

    @ParameterizedTest
    @EnumSource(Status.class)
    public void testStatusUpdate(Status status) {
        Customer newCustomer = service.add(new NewCustomer("customer " + Math.random(), mockContactDetails()));
        service.updateStatus(newCustomer.getId(), status);
        Customer updatedCustomer = service.getById(newCustomer.getId());
        assertThat(updatedCustomer.getStatus()).isEqualTo(status);
    }


    private ContactDetails mockContactDetails() {
        return new ContactDetails(ADDRESS, PHONE_NUMBER, TWITTER_HANDLE);
    }
}
