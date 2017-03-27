package com.ggsoft.poc.services.dto;

/**
 * Created by Axxiome on 3/26/2017.
 */
public final class MembershipDTOBuilder {
    private String id;
    private String name;

    private MembershipDTOBuilder() {
    }

    public static MembershipDTOBuilder aMembershipDTO() {
        return new MembershipDTOBuilder();
    }

    public MembershipDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public MembershipDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MembershipDTO build() {
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setId(id);
        membershipDTO.setName(name);
        return membershipDTO;
    }
}
