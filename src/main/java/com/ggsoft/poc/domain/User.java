package com.ggsoft.poc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Victor Gil on 3/23/2017.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @ManyToMany(mappedBy = "members")
    private List<Group> memberships;

    @Id
    private String id;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String streetAddress;
    @Column
    private String fullName;

    public List<Group> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Group> memberships) {
        this.memberships = memberships;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
