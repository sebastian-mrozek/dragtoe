package io.dt.api;

import java.util.Date;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String nickName;
    private final ContactDetails contactDetails;
    private final Date creationDateTime;
    private final Status status;

    public Customer(UUID id, String nickName, ContactDetails contactDetails, Date creationDateTime, Status status) {
        this.id = id;
        this.nickName = nickName;
        this.contactDetails = contactDetails;
        this.creationDateTime = creationDateTime;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public Status getStatus() {
        return status;
    }
}
