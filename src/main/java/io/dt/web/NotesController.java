package io.dt.web;

import io.avaje.http.api.*;
import io.dt.api.*;
import io.dt.service.api.ICustomersService;
import io.dt.service.api.INotesService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@Controller
@Path("customers/{customerId}/notes")
public class NotesController {

    private final INotesService notesService;

    @Inject
    public NotesController(INotesService notesService) {
        this.notesService = notesService;
    }

    @Get
    public List<Note> getAll(UUID customerId) {
        return notesService.getAll(customerId);
    }

    @Post
    public Note add(UUID customerId, NewNote newNote) {
        return notesService.add(customerId, newNote.getText());
    }

    @Delete("{noteId}")
    public void deleteNote(UUID noteId) {
        notesService.delete(noteId);
    }

    @Put("{noteId}")
    public void updateNote(UUID noteId, NewNote newNote) {
        notesService.update(noteId, newNote.getText());
    }
}
