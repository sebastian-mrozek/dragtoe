package io.dt.service;

import io.dt.api.Note;
import io.dt.api.service.INotesService;

import java.util.List;
import java.util.UUID;

public class NotesService implements INotesService {
    @Override
    public List<Note> getAll(UUID customerId) {
        return null;
    }

    @Override
    public Note add(UUID customerId, String text) {
        return null;
    }

    @Override
    public Note update(UUID noteId, String text) {
        return null;
    }

    @Override
    public void delete(UUID noteId) {

    }
}
