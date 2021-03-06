package io.dt.service;

import io.dt.api.Note;
import io.dt.mapper.NoteMapper;
import io.dt.service.api.INotesService;
import io.dt.service.db.DNote;
import io.dt.service.db.query.QDNote;

import javax.persistence.OptimisticLockException;
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
        var dNote = new DNote(null, customerId, text, 1);
        dNote.save();
        return mapper.dbToApi(dNote);
    }

    @Override
    public void update(Note updatedNote) {
        int updatedRecordsCount = new QDNote()
                .id.eq(updatedNote.getId())
                .version.eq(updatedNote.getVersion())
                .asUpdate()
                .set("text", updatedNote.getText())
                .set("version", updatedNote.getVersion() + 1)
                .update();
        if (updatedRecordsCount == 0) {
            throw new OptimisticLockException();
        }
    }

    @Override
    public void delete(UUID noteId) {
        new QDNote().id.eq(noteId).delete();
    }
}
