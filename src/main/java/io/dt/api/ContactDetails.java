package io.dt.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ContactDetails {
    private final String address;
    private final String phoneNumber;
    private final String twitterHandle;

    @JsonCreator
    public ContactDetails(
            @JsonProperty("address") String address,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("twitterHandle") String twitterHandle) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.twitterHandle = twitterHandle;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return address.equals(that.address) && phoneNumber.equals(that.phoneNumber) && twitterHandle.equals(that.twitterHandle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, phoneNumber, twitterHandle);
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", twitterHandle='" + twitterHandle + '\'' +
                '}';
    }
}
