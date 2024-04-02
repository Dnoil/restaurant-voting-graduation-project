package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import static com.github.Dnoil.restaurant_voting.util.Util.getEffectiveClass;

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

    public int id() {
        Assert.notNull(id, "Entity must have an id");
        return id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getEffectiveClass(this) != getEffectiveClass(o)) return false;
        return getId() != null && getId().equals(((BaseEntity) o).getId());
    }

    @Override
    public final int hashCode() {
        return getEffectiveClass(this).hashCode();
    }
}
