package io.dt.service.db;

import io.ebean.Model;
import io.ebean.annotation.Index;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class DNote extends Model {

    @Id
    public UUID id;

    @Index
    public UUID customerId;

    public String text;

    public DNote(UUID id, UUID customerId, String text) {
        this.id = id;
        this.customerId = customerId;
        this.text = text;
    }
}
