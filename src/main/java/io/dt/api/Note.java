package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Note {

    private final UUID id;
    private final String text;

    @JsonCreator
    public Note(
            @JsonProperty("id") UUID id,
            @JsonProperty("text") String text) {
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
