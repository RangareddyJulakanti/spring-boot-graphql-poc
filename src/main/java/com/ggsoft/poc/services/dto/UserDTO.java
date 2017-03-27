package com.ggsoft.poc.services.dto;

import java.util.List;

/**
 * Created by Axxiome on 3/26/2017.
 */
public class UserDTO extends UserWithoutMembershipsDTO {
    private List<MembershipDTO> memberships;

    public List<MembershipDTO> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MembershipDTO> memberships) {
        this.memberships = memberships;
    }

}
