package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class NewCustomer {
    private final String nickName;
    private final ContactDetails contactDetails;

    @JsonCreator
    public NewCustomer(
            @JsonProperty("nickName") String nickName,
            @JsonProperty("contactDetails") ContactDetails contactDetails) {
        this.nickName = nickName;
        this.contactDetails = contactDetails;
    }

    public String getNickName() {
        return nickName;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewCustomer that = (NewCustomer) o;
        return nickName.equals(that.nickName) && contactDetails.equals(that.contactDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName, contactDetails);
    }

    @Override
    public String toString() {
        return "NewCustomer{" +
                "nickName='" + nickName + '\'' +
                ", contactDetails=" + contactDetails +
                '}';
    }
}
