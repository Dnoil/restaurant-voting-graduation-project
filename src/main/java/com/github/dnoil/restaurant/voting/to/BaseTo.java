package com.github.dnoil.restaurant.voting.to;

import com.github.dnoil.restaurant.voting.model.HasId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseTo implements HasId {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
