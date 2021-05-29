package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
