package io.dt.service;

import io.dt.api.Note;
import io.dt.service.api.INotesService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesServiceTest {

    INotesService service = new NotesService();

    private UUID customerId;

    @BeforeEach
    public void setup() {
        customerId = UUID.randomUUID();
    }

    @Test
    public void testAdd() {
        String text = randomText();

        service.add(customerId, text);

        List<Note> notes = service.getAll(customerId);
        assertThat(notes.size()).isEqualTo(1);
        assertThat(notes.get(0).getText()).isEqualTo(text);
    }

    @Test
    public void testGetAll() {
        String[] expectedNotesStrings = { randomText(), randomText(), randomText() };

        service.add(customerId, expectedNotesStrings[0]);
        service.add(customerId, expectedNotesStrings[1]);
        service.add(customerId, expectedNotesStrings[2]);

        List<Note> actualNotes = service.getAll(customerId);
        List<String> actualNotesStrings = actualNotes.stream()
                .map(Note::getText)
                .collect(Collectors.toList());

        assertThat(actualNotesStrings).containsExactlyInAnyOrder(expectedNotesStrings);
    }

    @Test
    public void testDelete() {
        service.add(customerId, randomText());
        service.add(customerId, randomText());
        service.add(customerId, randomText());

        service.getAll(customerId).forEach(note -> service.delete(note.getId()));

        List<Note> allNotesAfterDelete = service.getAll(customerId);
        assertThat(allNotesAfterDelete.size()).isEqualTo(0);
    }

    @Test
    public void testUpdate() {
        Note expectedNote = service.add(customerId, randomText());
        String updatedText = randomText();
        service.update(expectedNote.getId(), updatedText);

        Note actualNote = service.getAll(customerId).get(0);
        assertThat(actualNote.getId()).isEqualTo(expectedNote.getId());
        assertThat(actualNote.getText()).isEqualTo(updatedText);
    }

    private String randomText() {
        return String.join(" ",
                RandomString.make(7),
                RandomString.make(12),
                RandomString.make(5));
    }
}
