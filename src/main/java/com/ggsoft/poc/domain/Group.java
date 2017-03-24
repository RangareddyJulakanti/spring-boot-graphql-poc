package com.ggsoft.poc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Victor Gil on 3/24/2017.
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_members"
    )
    private Set<User> members;

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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
