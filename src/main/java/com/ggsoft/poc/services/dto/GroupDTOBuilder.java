package com.ggsoft.poc.services.dto;

import java.util.Set;

/**
 * Created by Victor Gil on 3/26/2017.
 */
public final class GroupDTOBuilder {
    private String id;
    private String name;
    private Set<UserWithoutMembershipsDTO> members;

    private GroupDTOBuilder() {
    }

    public static GroupDTOBuilder aGroupDTO() {
        return new GroupDTOBuilder();
    }

    public GroupDTOBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public GroupDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDTOBuilder withMembers(Set<UserWithoutMembershipsDTO> members) {
        this.members = members;
        return this;
    }

    public GroupDTO build() {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(id);
        groupDTO.setName(name);
        groupDTO.setMembers(members);
        return groupDTO;
    }
}
