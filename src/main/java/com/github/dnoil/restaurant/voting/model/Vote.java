package com.github.dnoil.restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "day"}, name = "single_vote_from_user_per_day"),
        indexes = @Index(columnList = "voted_time", name = "voted_time_idx"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity {

    @Column(name = "day", nullable = false, columnDefinition = "date default current_date")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate day = LocalDate.now();

    @Column(name = "voted_time", nullable = false, columnDefinition = "time default current_time")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "00:00:00")
    private LocalTime votedTime = LocalTime.now();

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private User user;

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(implementation = Integer.class)
    private Restaurant restaurant;

    public Vote(Integer id, User user, Restaurant restaurant) {
        super.setId(id);
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + getId() + '\'' +
                ", day=" + day +
                ", votedTime=" + votedTime +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
