package com.ggsoft.poc.services.dto;

import java.io.Serializable;

/**
 * Created by Victor Gil on 3/26/2017.
 */
public class MembershipDTO implements Serializable {
    private String id;

    private String name;

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
}
