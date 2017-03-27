package com.ggsoft.poc.services.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Victor Gil on 3/26/2017.
 */
public class GroupDTO implements Serializable {
    private String id;

    private String name;

    private Set<UserWithoutMembershipsDTO> members;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserWithoutMembershipsDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<UserWithoutMembershipsDTO> members) {
        this.members = members;
    }
}
