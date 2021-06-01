package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Note {

    private final UUID id;
    private final String text;
    private final long version;

    @JsonCreator
    public Note(
            @JsonProperty("id") UUID id,
            @JsonProperty("text") String text,
            @JsonProperty("version") long version) {
        this.id = id;
        this.text = text;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return version == note.version && id.equals(note.id) && text.equals(note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, version);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", version=" + version +
                '}';
    }
}
