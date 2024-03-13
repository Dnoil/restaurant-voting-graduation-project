package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseTo {
    private Integer id;
    private String name;

    public BaseTo(BaseEntity baseEntity) {
        this.id = baseEntity.getId();
        this.name = baseEntity.getName();
    }
}
