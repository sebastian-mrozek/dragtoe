package io.dt.service.api;

import io.dt.api.Note;

import java.util.List;
import java.util.UUID;

public interface INotesService {

    List<Note> getAll(UUID customerId);

    Note add(UUID customerId, String text);

    void update(Note updatedNote);

    void delete(UUID noteId);
}
