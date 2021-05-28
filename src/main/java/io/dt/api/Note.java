package io.dt.api;

import java.util.UUID;

public class Note {

    private final UUID id;
    private final String text;

    public Note(UUID id, String text) {
        this.id = id;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
