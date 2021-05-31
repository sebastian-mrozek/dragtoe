package io.dt.web;

import io.dt.api.Customer;
import io.dt.util.TestClient;
import io.dt.util.TestUtil;
import io.ebean.test.Json;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomersEndpointTest {

    static final int PORT = 12345;
    static Application app;
    static TestClient testClient = new TestClient("http://localhost:" + PORT + "/customers/");

    @BeforeAll
    public static void setup() {
        app = new Application();
        app.start(PORT);
    }

    @Test
    public void testGetAll() {
        Customer customer1 = JavalinJson.fromJson(testClient.postResource("getAll_new-customer-1.json").getBody(), Customer.class);
        Customer customer2 = JavalinJson.fromJson(testClient.postResource("getAll_new-customer-2.json").getBody(), Customer.class);
        HttpResponse<String> response = testClient.get();
        List<Customer> actualCustomers = Json.readList(Customer.class, response.getBody());
        Assertions.assertThat(actualCustomers).contains(customer1, customer2);
    }

    @Test
    public void testCreateCustomer() {
        HttpResponse<String> postResponse = testClient.postResource("createCustomer_new-customer.json");
        TestUtil.assertResponse(postResponse, 201, "createCustomer_expected.json");
        Customer newCustomer = JavalinJson.fromJson(postResponse.getBody(), Customer.class);

        HttpResponse<String> getResponse = testClient.get(newCustomer.getId().toString());
        TestUtil.assertResponse(getResponse, 200, "createCustomer_expected.json");
    }

    @Test
    public void testUpdateStatus() {
        HttpResponse<String> postResponse = testClient.postResource("updateStatus_new-customer.json");
        Customer newCustomer = JavalinJson.fromJson(postResponse.getBody(), Customer.class);

        testClient.patch(newCustomer.getId().toString(), "updateStatus_patch.json");

        HttpResponse<String> getResponse = testClient.get(newCustomer.getId().toString());
        TestUtil.assertResponse(getResponse, 200, "updateStatus_expected.json");

    }

}
