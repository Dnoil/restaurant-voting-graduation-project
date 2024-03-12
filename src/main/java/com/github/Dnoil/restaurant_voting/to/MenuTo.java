package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import lombok.Data;

import java.util.Date;

@Data
public class MenuTo extends BaseTo {
    private Date date;
    private Restaurant restaurant;
}
