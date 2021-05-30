package io.dt.web;

import io.dt.api.Customer;
import io.dt.util.TestClient;
import io.dt.util.TestUtil;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        testClient.postResource("getAll_new-customer-1.json");
        testClient.postResource("getAll_new-customer-2.json");
        HttpResponse<String> response = testClient.get();
        TestUtil.assertResponse(response, 200, "getAll_expected.json");
    }

    @Test
    public void testCreateCustomer() {
        HttpResponse<String> postResponse = testClient.postResource("createCustomer_new-customer.json");
        TestUtil.assertResponse(postResponse, 201, "createCustomer_expected.json");
        Customer newCustomer = JavalinJson.fromJson(postResponse.getBody(), Customer.class);

        HttpResponse<String> getResponse = testClient.get(newCustomer.getId().toString());
        TestUtil.assertResponse(getResponse, 200, "createCustomer_expected.json");

    }

}
