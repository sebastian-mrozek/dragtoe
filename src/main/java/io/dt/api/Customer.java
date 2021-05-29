package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String nickName;
    private final ContactDetails contactDetails;
    private final Date creationDateTime;
    private final Status status;

    @JsonCreator
    public Customer(
            @JsonProperty("id") UUID id,
            @JsonProperty("nickName") String nickName,
            @JsonProperty("contactDetails") ContactDetails contactDetails,
            @JsonProperty("creationDateTime") Date creationDateTime,
            @JsonProperty("status") Status status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && nickName.equals(customer.nickName) && contactDetails.equals(customer.contactDetails) && creationDateTime.equals(customer.creationDateTime) && status == customer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, contactDetails, creationDateTime, status);
    }
}
