package io.dt.mapper;

import io.dt.api.Note;
import io.dt.service.db.DNote;

import java.util.UUID;

public class NoteMapper {

    public Note dbToApi(DNote dNote) {
        return new Note(dNote.id, dNote.text);
    }

    public DNote apiToDb(UUID customerId, Note note) {
        return new DNote(customerId, note.getId(), note.getText());
    }
}
