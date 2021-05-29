package io.dt.service.db;

import io.dt.api.Status;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class DCustomer extends Model {

    @Id
    public UUID id;

    public String nickName;

    @Embedded
    public DContactDetails contactDetails;

    @WhenCreated
    public Date creationDateTime;

    public Status status;

    public DCustomer(UUID id, String nickName, DContactDetails contactDetails, Date creationDateTime, Status status) {
        this.id = id;
        this.nickName = nickName;
        this.contactDetails = contactDetails;
        this.creationDateTime = creationDateTime;
        this.status = status;
    }
}
