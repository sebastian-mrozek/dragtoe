package io.dt.service;

import io.dt.api.Note;
import io.dt.mapper.NoteMapper;
import io.dt.service.api.INotesService;
import io.dt.service.db.DNote;
import io.dt.service.db.query.QDNote;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotesService implements INotesService {

    NoteMapper mapper = new NoteMapper();

    @Override
    public List<Note> getAll(UUID customerId) {
        return new QDNote()
                .customerId.eq(customerId)
                .findList().stream()
                .map(mapper::dbToApi)
                .collect(Collectors.toList());
    }

    @Override
    public Note add(UUID customerId, String text) {
        DNote dNote = new DNote(null, customerId, text);
        dNote.save();
        return mapper.dbToApi(dNote);
    }

    @Override
    public void update(UUID noteId, String text) {
        new QDNote().id.eq(noteId)
                .asUpdate()
                .set("text", text)
                .update();
    }

    @Override
    public void delete(UUID noteId) {
        new QDNote().id.eq(noteId).delete();
    }
}
