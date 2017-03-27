package com.ggsoft.poc.services.dto;

/**
 * Created by Axxiome on 3/26/2017.
 */
public final class UserWithoutMembershipsDTOBuilder {
    private String id;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String fullName;

    private UserWithoutMembershipsDTOBuilder() {
    }

    public static UserWithoutMembershipsDTOBuilder anUserWithoutMembershipsDTO() {
        return new UserWithoutMembershipsDTOBuilder();
    }

    public UserWithoutMembershipsDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserWithoutMembershipsDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserWithoutMembershipsDTOBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserWithoutMembershipsDTOBuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public UserWithoutMembershipsDTOBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserWithoutMembershipsDTO build() {
        UserWithoutMembershipsDTO userWithoutMembershipsDTO = new UserWithoutMembershipsDTO();
        userWithoutMembershipsDTO.setId(id);
        userWithoutMembershipsDTO.setEmail(email);
        userWithoutMembershipsDTO.setPhoneNumber(phoneNumber);
        userWithoutMembershipsDTO.setStreetAddress(streetAddress);
        userWithoutMembershipsDTO.setFullName(fullName);
        return userWithoutMembershipsDTO;
    }
}
