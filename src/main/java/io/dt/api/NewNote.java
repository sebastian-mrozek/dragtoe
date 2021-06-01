package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class NewNote {
    private final String text;

    @JsonCreator
    public NewNote(@JsonProperty("text") String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewNote newNote = (NewNote) o;
        return text.equals(newNote.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "NewNote{" +
                "text='" + text + '\'' +
                '}';
    }
}
