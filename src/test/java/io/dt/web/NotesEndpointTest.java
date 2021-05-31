package io.dt.web;

import io.dt.api.Customer;
import io.dt.api.Note;
import io.dt.util.TestClient;
import io.ebean.test.Json;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesEndpointTest {

    static final int PORT = 54321;
    static Application app;
    static TestClient testClient = new TestClient("http://localhost:" + PORT + "/customers/");

    @BeforeAll
    public static void setup() {
        app = new Application();
        app.start(PORT);

        HttpResponse<String> response = testClient.postResource("createCustomer_new-customer.json");
        UUID customerId = JavalinJson.fromJson(response.getBody(), Customer.class).getId();
        testClient = new TestClient("http://localhost:" + PORT + "/customers/" + customerId + "/notes/");
    }

    @Test
    public void testAllCauseNotEnoughTime() {
        testClient.postText(newNote("My new note 1"));
        testClient.postText(newNote("My new note 2"));

        HttpResponse<String> notesListResponse = testClient.get();
        List<Note> notes = Json.readList(Note.class, notesListResponse.getBody());
        assertThat(notes.size()).isEqualTo(2);
        assertThat(notes.get(0).getText()).isEqualTo("My new note 1");
        assertThat(notes.get(1).getText()).isEqualTo("My new note 2");

        testClient.putText(notes.get(0).getId().toString(), newNote("Updated note 1"));
        HttpResponse<String> notesListResponse2 = testClient.get();
        List<Note> notes2 = Json.readList(Note.class, notesListResponse2.getBody());
        assertThat(notes2.size()).isEqualTo(2);
        assertThat(notes2.get(0).getText()).isEqualTo("Updated note 1");
        assertThat(notes2.get(1).getText()).isEqualTo("My new note 2");

        testClient.delete(notes.get(1).getId().toString());
        HttpResponse<String> notesListResponse3 = testClient.get();
        List<Note> notes3 = Json.readList(Note.class, notesListResponse3.getBody());
        assertThat(notes3.size()).isEqualTo(1);
        assertThat(notes3.get(0).getText()).isEqualTo("Updated note 1");
    }

    private String newNote(String text) {
        return "{\"text\":\"" + text + "\"}";
    }
}
