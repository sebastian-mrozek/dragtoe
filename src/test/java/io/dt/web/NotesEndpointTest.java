package io.dt.web;

import io.dt.api.Customer;
import io.dt.api.NewNote;
import io.dt.api.Note;
import io.dt.util.TestClient;
import io.ebean.test.Json;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesEndpointTest {

    static final AtomicInteger PORT = new AtomicInteger(54321);
    Application app;
    TestClient testClient;

    @BeforeEach
    public void setup() {
        app = new Application();
        int port = PORT.incrementAndGet();
        app.start(port);

        testClient = new TestClient("http://localhost:" + port + "/customers/");
        HttpResponse<String> response = testClient.postResource("createCustomer_new-customer.json");
        UUID customerId = JavalinJson.fromJson(response.getBody(), Customer.class).getId();

        testClient = new TestClient("http://localhost:" + port + "/customers/" + customerId + "/notes/");
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

        Note noteToUpdate = notes.get(0);
        testClient.putText(updatedNote(noteToUpdate.getId(), "Updated note 1", noteToUpdate.getVersion()));
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

    @Test
    public void testOutOfDateUpdate() {
        HttpResponse<String> newNoteResponse = testClient.postText(newNote("My first note"));
        Note newNote = JavalinJson.fromJson(newNoteResponse.getBody(), Note.class);

        testClient.putText(updatedNote(newNote.getId(), "Very important update", newNote.getVersion()));
        Note updatedNote = getFirstNote();
        assertThat(updatedNote.getText()).isEqualTo("Very important update");

        testClient.putText(updatedNote(updatedNote.getId(), "A failed update", newNote.getVersion()));
        Note notUpdatedNote = getFirstNote();
        assertThat(notUpdatedNote.getText()).isEqualTo("Very important update");

        testClient.putText(updatedNote(updatedNote.getId(), "Another update", updatedNote.getVersion()));
        Note finallyUpdatedNote = getFirstNote();
        assertThat(finallyUpdatedNote.getText()).isEqualTo("Another update");
    }

    private Note getFirstNote() {
        HttpResponse<String> notesListResponse = testClient.get();
        List<Note> notes = Json.readList(Note.class, notesListResponse.getBody());
        return notes.get(0);
    }

    private String updatedNote(UUID id, String newText, long version) {
        return JavalinJson.toJson(new Note(id, newText, version));
    }

    private String newNote(String text) {
        return JavalinJson.toJson(new NewNote(text));
    }
}
