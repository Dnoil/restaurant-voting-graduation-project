package com.github.dnoil.restaurant.voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "vote", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "voted_date_time"}, name = "single_vote_from_user_per_day"),
indexes = @Index(columnList = "voted_date_time", name = "date_time_idx"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity {

    @Column(name = "voted_date_time", nullable = false)
    @NotNull
    private LocalDateTime votedDateTime = LocalDateTime.now();

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote(Integer id, User user, Restaurant restaurant) {
        super.setId(id);
        super.setName(user.getName() + "'s vote");
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", votedTime=" + votedDateTime +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}