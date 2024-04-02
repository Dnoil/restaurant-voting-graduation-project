package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
public class Vote extends BaseEntity {

    @Column(name = "time", nullable = false)
    @NotNull
    private LocalDateTime time = LocalDateTime.now();

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote(User user, Restaurant restaurant) {
        super.setName(user.getName() + "'s vote");
        this.user = user;
        this.restaurant = restaurant;
    }
}
