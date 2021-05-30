package io.dt.util;

import io.ebean.test.Json;
import kong.unirest.HttpResponse;
import org.assertj.core.api.Assertions;

public class TestUtil {

    public static void assertResponse(HttpResponse<String> actualResponse, int expectedCode, String expectedResource) {
        String expectedContent = Json.readResource("/" + expectedResource);
        Assertions.assertThat(actualResponse.getStatus()).isEqualTo(expectedCode);
        Json.assertContains(actualResponse.getBody(), expectedContent);
    }
}
