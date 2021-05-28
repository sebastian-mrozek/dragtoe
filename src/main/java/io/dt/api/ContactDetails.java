package io.dt.api;

public class ContactDetails {
    private final String address;
    private final String phoneNumber;
    private final String twitterHandle;

    public ContactDetails(String address, String phoneNumber, String twitterHandle) {
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
}
