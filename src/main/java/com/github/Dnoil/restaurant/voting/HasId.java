package com.github.Dnoil.restaurant.voting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;


public interface HasId {
    Integer getId();

    void setId(Integer id);

    default int id() {
        Assert.notNull(getId(), "Entity must have an id");
        return getId();
    }

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }
}
