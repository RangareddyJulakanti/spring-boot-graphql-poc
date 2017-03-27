package com.ggsoft.poc.services.dto;

import java.util.List;

/**
 * Created by Victor Gil on 3/26/2017.
 */
public final class UserDTOBuilder {
    private String id;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String fullName;
    private List<MembershipDTO> memberships;

    private UserDTOBuilder() {
    }

    public static UserDTOBuilder anUserDTO() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDTOBuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public UserDTOBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserDTOBuilder withMemberships(List<MembershipDTO> memberships) {
        this.memberships = memberships;
        return this;
    }

    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setStreetAddress(streetAddress);
        userDTO.setFullName(fullName);
        userDTO.setMemberships(memberships);
        return userDTO;
    }
}
