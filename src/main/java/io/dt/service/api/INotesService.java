package io.dt.service.api;

import io.dt.api.Note;

import java.util.List;
import java.util.UUID;

public interface INotesService {

    List<Note> getAll(UUID customerId);

    Note add(UUID customerId, String text);

    Note update(UUID noteId, String text);

    void delete(UUID noteId);
}
