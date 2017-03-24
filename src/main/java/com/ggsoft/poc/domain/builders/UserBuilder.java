package com.ggsoft.poc.domain.builders;

import com.ggsoft.poc.domain.User;

/**
 * Created by Victor Gil on 3/23/2017.
 */
public final class UserBuilder {
    private
    String id;
    private
    String email;
    private
    String phoneNumber;
    private
    String streetAddress;
    private
    String fullName;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public UserBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setStreetAddress(streetAddress);
        user.setFullName(fullName);
        return user;
    }
}
