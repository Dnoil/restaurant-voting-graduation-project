package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
@Access(AccessType.FIELD)
@Getter
@Setter
public abstract class BaseEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
