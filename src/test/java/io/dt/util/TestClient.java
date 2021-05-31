package io.dt.util;

import io.ebean.test.Json;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class TestClient {

    private final String apiBaseUrl;

    public TestClient(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public HttpResponse<String> postResource(String resourceName) {
        String content = Json.readResource("/" + resourceName);
        return Unirest.post(apiBaseUrl).body(content).asString();
    }

    public HttpResponse<String> get(String path) {
        return Unirest.get(apiBaseUrl + path).asString();
    }

    public HttpResponse<String> patch(String path, String resourceName) {
        String content = Json.readResource("/" + resourceName);
        return Unirest.patch(apiBaseUrl + path).body(content).asString();
    }

    public HttpResponse<String> delete(String path) {
        return Unirest.delete(apiBaseUrl + path).asString();
    }

    public HttpResponse<String> get() {
        return get("");
    }
}
