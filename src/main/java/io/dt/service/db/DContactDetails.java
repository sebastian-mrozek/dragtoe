package io.dt.service.db;

import io.ebean.Model;

import javax.persistence.Embeddable;

@Embeddable
public class DContactDetails extends Model {

    public String address;

    public String phoneNumber;

    public String twitterHandle;

    public DContactDetails(String address, String phoneNumber, String twitterHandle) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.twitterHandle = twitterHandle;
    }
}
