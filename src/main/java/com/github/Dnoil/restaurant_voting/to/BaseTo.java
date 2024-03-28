package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.BaseEntity;

public class BaseTo {
    private final Integer id;
    private final String name;

    public BaseTo(BaseEntity baseEntity) {
        this.id = baseEntity.getId();
        this.name = baseEntity.getName();
    }
}
