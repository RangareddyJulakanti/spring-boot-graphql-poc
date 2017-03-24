package com.ggsoft.poc.domain.builders;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;

import java.util.Set;

/**
 * Created by Victor Gil on 3/24/2017.
 */
public final class GroupBuilder {
    private String id;
    private String name;
    private Set<User> members;

    private GroupBuilder() {
    }

    public static GroupBuilder aGroup() {
        return new GroupBuilder();
    }

    public GroupBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public GroupBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupBuilder withMembers(Set<User> members) {
        this.members = members;
        return this;
    }

    public Group build() {
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setMembers(members);
        return group;
    }
}
